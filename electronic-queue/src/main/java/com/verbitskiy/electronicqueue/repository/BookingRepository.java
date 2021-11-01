package com.verbitskiy.electronicqueue.repository;

import com.verbitskiy.electronicqueue.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
