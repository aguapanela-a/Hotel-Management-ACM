package com.acm.sgh.auth.entities;

import com.acm.sgh.auth.enumeration.Role;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //relación uno a uno de empleado con usuario
    @JoinColumn(name = "user_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    private Role role;
}
