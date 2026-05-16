package com.acm.sgh.servicesManagement.entitie;

import com.acm.sgh.hotelManagement.entities.Hotel;
import com.acm.sgh.servicesManagement.enumeration.ServiceType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class OtherService {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private ServiceType serviceType;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

}
