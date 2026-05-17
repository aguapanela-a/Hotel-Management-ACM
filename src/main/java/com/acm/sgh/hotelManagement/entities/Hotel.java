package com.acm.sgh.hotelManagement.entities;

import com.acm.sgh.roomManagement.entities.HotelRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hotel_branch")
    private String hotelBranch;

    @Column(name = "hotel_name", unique = true, nullable = false, length = 150)
    private String hotelName;

    @Column(name = "hotel_address", nullable = false)
    private String hotelAddress;

    @Column(name = "hotel_city", nullable = false, length = 100)
    private String hotelCity;

    @Column(name = "hotel_phone", unique = true, length = 20)
    private String hotelPhone;

    @Column(name = "hotel_email", unique = true, length = 150)
    private String hotelEmail;

    @Column(name = "hotel_category", length = 50)
    private String hotelCategory;

    @Column(name = "hotel_status", length = 30)
    private String hotelStatus;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<HotelRoom> roomList;
}
