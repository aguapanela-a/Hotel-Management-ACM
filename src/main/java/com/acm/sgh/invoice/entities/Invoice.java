package com.acm.sgh.invoice.entities;

import com.acm.sgh.paymentManagement.entities.Payment;
import jakarta.persistence.*;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int UUID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
