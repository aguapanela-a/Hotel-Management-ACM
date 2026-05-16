package com.acm.sgh.reservationsManagement.entities;

import com.acm.sgh.auth.entities.Customer;
import com.acm.sgh.reservationsManagement.enumerations.ReservationStatus;
import com.acm.sgh.roomManagement.entities.HotelRoom;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private HotelRoom room;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private OtherService otherService;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Customer customer;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private ReservationStatus status;
}
