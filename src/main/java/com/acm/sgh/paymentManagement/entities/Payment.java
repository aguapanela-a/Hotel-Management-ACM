package com.acm.sgh.paymentManagement.entities;

import com.acm.sgh.paymentManagement.enumeration.PaymentType;
import com.acm.sgh.reservationsManagement.entities.Reservation;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private PaymentType paymentType;
    private BigDecimal amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
