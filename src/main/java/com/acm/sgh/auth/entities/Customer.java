package com.acm.sgh.auth.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //Relación uno a uno de empleado con usuario
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", unique=true)
    private User user;
}
