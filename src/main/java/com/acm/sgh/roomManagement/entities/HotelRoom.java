package com.acm.sgh.roomManagement.entities;

import com.acm.sgh.hotelManagement.entities.Hotel;
import com.acm.sgh.roomManagement.enumeration.RoomType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private String capacity;
    private String price;
    private String availability;
    private boolean status;
    private int floor;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

}
