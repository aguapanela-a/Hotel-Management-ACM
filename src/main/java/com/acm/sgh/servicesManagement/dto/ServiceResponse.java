package com.acm.sgh.servicesManagement.dto;

import com.acm.sgh.servicesManagement.enumeration.ServiceType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ServiceResponse {
    private UUID id;
    private ServiceType serviceType;
    private String serviceName;
    private String description;
    private BigDecimal price;
    private UUID hotelId;
    private String hotelName;
}
