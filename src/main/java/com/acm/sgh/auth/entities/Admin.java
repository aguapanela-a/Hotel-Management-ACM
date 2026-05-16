package com.acm.sgh.auth.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL )
    private User user;



}
