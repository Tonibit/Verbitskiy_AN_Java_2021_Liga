package com.verbitskiy.electronicqueue.service.interfaces;

import com.verbitskiy.electronicqueue.dto.BookingDTO;
import com.verbitskiy.electronicqueue.entity.Booking;
import com.verbitskiy.electronicqueue.form.FormToChangeStatus;

import java.util.List;

public interface BookingService {

    Booking addNewBooking(BookingDTO booking);

    List<Booking> getActiveBookingsForUser(String username);

    void changeBookingStatusToArrived(FormToChangeStatus changeForm);

    void changeBookingStatusToDone(Long bookingId);

    BookingDTO getFirstActiveBooking();

    void deleteBooking(Long bookingId);
}
