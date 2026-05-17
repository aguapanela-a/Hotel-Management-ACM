package com.acm.sgh.auth.entities;

import com.acm.sgh.auth.enumeration.UserType;
import com.acm.sgh.hotelManagement.entities.Hotel; // Import Hotel entity
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 20)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
