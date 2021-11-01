package com.verbitskiy.electronicqueue.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time_booking")
    private LocalDateTime bookingDateTime;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @Column(name = "arrived")
    private boolean isArrived;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "completed")
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
