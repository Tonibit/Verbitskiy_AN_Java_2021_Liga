package com.verbitskiy.electronicqueue.controller;

import com.verbitskiy.electronicqueue.dto.BookingDTO;
import com.verbitskiy.electronicqueue.form.FormToChangeStatus;
import com.verbitskiy.electronicqueue.service.interfaces.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @GetMapping("/bookings/users/{username}")
    public List<BookingDTO> getActiveBookingsForUser(@PathVariable("username") String username) {
        return bookingService.getActiveBookingsForUser(username).stream()
                .map(BookingDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/bookings/users")
    public BookingDTO addBooking(@RequestBody BookingDTO bookingDTO) {
        return new BookingDTO(bookingService.addNewBooking(bookingDTO));
    }

    @PutMapping("/bookings/users")
    public void markBookingArrival(@RequestBody FormToChangeStatus changeForm) {
        bookingService.changeBookingStatusToArrived(changeForm);
    }

    @PutMapping("/bookings/admins/{id}")
    public void markBookingCompleted(@PathVariable("id") Long bookingId) {
        bookingService.changeBookingStatusToDone(bookingId);
    }

    @GetMapping("/bookings/admins")
    public BookingDTO getFirstActiveBooking() {
        return bookingService.getFirstActiveBooking();
    }

    @DeleteMapping("/bookings/admins/{id}")
    public void deleteBooking(@PathVariable("id") Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
