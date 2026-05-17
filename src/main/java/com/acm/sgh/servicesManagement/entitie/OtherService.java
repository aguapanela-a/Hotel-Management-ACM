package com.acm.sgh.servicesManagement.entitie;

import com.acm.sgh.hotelManagement.entities.Hotel;
import com.acm.sgh.servicesManagement.enumeration.ServiceType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "other_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtherService {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false, length = 30)
    private ServiceType serviceType;

    @Column(name = "service_name", nullable = false, length = 150)
    private String serviceName;

    @Column(length = 255)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    @ToString.Exclude
    private Hotel hotel;
}
