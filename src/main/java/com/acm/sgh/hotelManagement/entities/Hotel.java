package com.acm.sgh.hotelManagement.entities;

import com.acm.sgh.roomManagement.entities.HotelRoom;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String hotelBranch;

    @Column(unique = true)
    private String hotelName;
    private String hotelAddress;
    private String hotelCity;

    @Column(unique = true)
    private String hotelPhone;

    @Column(unique = true)
    private String hotelEmail;
    private String hotelCategory;
    private String hotelStatus;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelRoom> roomList;

}
