package com.verbitskiy.electronicqueue.service;

import com.verbitskiy.electronicqueue.dto.BookingDTO;
import com.verbitskiy.electronicqueue.entity.AppUser;
import com.verbitskiy.electronicqueue.entity.Booking;
import com.verbitskiy.electronicqueue.exception.BookingFoundException;
import com.verbitskiy.electronicqueue.exception.IncorrectBookingDateTimeException;
import com.verbitskiy.electronicqueue.exception.UserFoundException;
import com.verbitskiy.electronicqueue.form.FormToChangeStatus;
import com.verbitskiy.electronicqueue.repository.BookingRepository;
import com.verbitskiy.electronicqueue.repository.UserRepository;
import com.verbitskiy.electronicqueue.service.interfaces.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    private static final Integer STARTING_HOURS = 8;
    private static final Integer CLOSING_HOURS = 19;
    private static final Integer WORKING_DAYS = 5; //имеется ввиду сколько дней в неделю работает сервис

    @Transactional
    @Override
    public Booking addNewBooking(BookingDTO booking) {
        Booking newBooking = new Booking();
        newBooking.setBookingDateTime(booking.getDateTime());

        checkDateAndTimeIsCorrect(newBooking);
        checkIfTimeFreeForBooking(newBooking);

        newBooking.setActive(true);
        newBooking.setDone(false);
        newBooking.setArrived(false);
        newBooking.setDateOfCreation(LocalDateTime.now());
        AppUser user = userRepository.findById(booking.getUserId())
                .orElseThrow(() -> new UserFoundException("Could not find user"));
        newBooking.setUser(user);
        return bookingRepository.save(newBooking);
    }

    @Override
    public List<Booking> getActiveBookingsForUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserFoundException("Could not find user with login: " + username))
                .getBookingList().stream().filter(Booking::isActive).collect(Collectors.toList());
    }

    //Пользователь отмечается, что пришёл.

    @Transactional
    @Override
    public void changeBookingStatusToArrived(FormToChangeStatus changeForm) {
        Booking booking = bookingRepository.findById(changeForm.getBookingId())
                .orElseThrow(() -> new BookingFoundException("Booking doesn't exist"));
        if (!(booking.isActive() && booking.getUser().getUsername().equals(changeForm.getUsername()))) {
            throw new BookingFoundException("Booking is not active or doesn't belong to " + changeForm.getUsername());
        }
        booking.setArrived(true);
        bookingRepository.save(booking);
    }

    @Transactional
    @Override
    public void changeBookingStatusToDone(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingFoundException("Booking doesn't exist"));
        if (!booking.isActive()) {
            throw new IncorrectBookingDateTimeException("Booking is not active");
        }
        booking.setDone(true);
        booking.setActive(false);
        bookingRepository.save(booking);
    }


    //Админ получает первую/ближайшую активную бронь
    @Override
    public BookingDTO getFirstActiveBooking() {
        List<BookingDTO> bookingDTOList = bookingRepository.findAll().stream()
                .filter(Booking::isActive)
                .filter(booking -> !booking.getBookingDateTime().toLocalDate().isBefore(LocalDate.now()))
                .map(BookingDTO::new).collect(Collectors.toList());
        if (bookingDTOList.isEmpty()) {
            throw new BookingFoundException("List is Empty");
        }
        List<BookingDTO> activeBookingStartsFromNow = new ArrayList<>();
        for (BookingDTO booking : bookingDTOList) {
            if (booking.getDateTime().toLocalDate().isEqual(LocalDate.now())
                    && booking.getDateTime().toLocalTime().isBefore(LocalTime.now())) {
                continue;
            }
            activeBookingStartsFromNow.add(booking);

        }
        Collections.sort(activeBookingStartsFromNow);

        return activeBookingStartsFromNow.get(0);
    }

    @Transactional
    @Override
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingFoundException("Booking doesn't exist"));
        bookingRepository.delete(booking);
    }
    //Проверка не указана ли дата или время брони в прошлом

    private void checkDateAndTimeIsCorrect(Booking booking) {
        boolean isDateInThePast = booking.getBookingDateTime().toLocalDate().isBefore(LocalDate.now());
        boolean isTimeInThePast = booking.getBookingDateTime().toLocalTime().isBefore(LocalTime.now().plusHours(1));
        if (isDateInThePast) {
            throw new IncorrectBookingDateTimeException("Date of booking in the past");
        }
        if (isTimeInThePast) {
            throw new IncorrectBookingDateTimeException("Time of booking is in the past or too close to now time " +
                    "(the closet time should be after 1 hour from now)");
        }
        if ((WORKING_DAYS - booking.getBookingDateTime().getDayOfWeek().getValue() < 0) ||
                booking.getBookingDateTime().getHour() < STARTING_HOURS
                || booking.getBookingDateTime().getHour() > CLOSING_HOURS - 1) {
            throw new IncorrectBookingDateTimeException("Working hours from " + STARTING_HOURS +
                    " till " + CLOSING_HOURS + ". Working Days from Monday till Friday");
        }
    }

    //Проверка не занято ли время для новой брони
    private void checkIfTimeFreeForBooking(Booking bookingForCheck) {
        List<Booking> allBookings = bookingRepository.findAll();
        for (Booking busyBooking : allBookings) {
            if (busyBooking.getBookingDateTime().toLocalDate()
                    .isEqual(bookingForCheck.getBookingDateTime().toLocalDate())) {
                int timeInMinForBusyBooking = busyBooking.getBookingDateTime().getHour() * 60
                        + busyBooking.getBookingDateTime().getMinute();
                int timeInMinForNewBooking = bookingForCheck.getBookingDateTime().getHour() * 60
                        + bookingForCheck.getBookingDateTime().getMinute();
                if (Math.abs(timeInMinForBusyBooking - timeInMinForNewBooking) < 15) {
                    throw new IncorrectBookingDateTimeException("Time of booking is busy");
                }
            }
        }
    }

    @Transactional
    @Scheduled(cron = "0 0/60 8-19 * * MON-FRI")
    public void changeStatus() {
        List<Booking> checkBookingList = bookingRepository.findAll();
        List<Booking> bookingsToUpdate = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Booking booking : checkBookingList) {
            if (booking.getBookingDateTime().isBefore(now) && booking.isActive()) {
                booking.setActive(false);
                bookingsToUpdate.add(booking);
            }
        }
        bookingRepository.saveAll(bookingsToUpdate);
    }

}
