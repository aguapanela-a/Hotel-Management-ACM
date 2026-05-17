package com.acm.sgh.servicesManagement.dto;

import com.acm.sgh.servicesManagement.enumeration.ServiceType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ServiceRequest {

    @NotNull(message = "El tipo de servicio es obligatorio")
    private ServiceType serviceType;

    @NotBlank(message = "El nombre del servicio es obligatorio")
    private String serviceName;

    private String description;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
    private BigDecimal price;

    @NotNull(message = "El ID del hotel es obligatorio")
    private UUID hotelId;
}
