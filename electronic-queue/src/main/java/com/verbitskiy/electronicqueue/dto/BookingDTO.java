package com.verbitskiy.electronicqueue.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.verbitskiy.electronicqueue.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BookingDTO implements Comparable<BookingDTO> {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")

    private LocalDateTime dateTime;
    private Long userId;

    public BookingDTO(Booking booking) {
        userId = booking.getUser().getId();
        dateTime = booking.getBookingDateTime();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "Date=" + dateTime.toLocalDate() +
                ", Time=" + dateTime.toLocalTime() +
                '}';
    }

    @Override
    public int compareTo(BookingDTO otherBooking) {
        return this.getDateTime().compareTo(otherBooking.getDateTime());
    }
}
