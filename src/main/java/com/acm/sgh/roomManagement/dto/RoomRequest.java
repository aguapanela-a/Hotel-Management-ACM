package com.acm.sgh.roomManagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class RoomRequest {

    @NotNull(message = "El tipo de habitación es obligatorio")
    private UUID roomTypeId; // Changed from RoomType to UUID

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad mínima es 1")
    private Integer capacity;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    private String availability;

    private Boolean status;

    @NotNull(message = "El piso es obligatorio")
    @Min(value = 1, message = "El piso mínimo es 1")
    private Integer floor;

    @NotNull(message = "El ID del hotel es obligatorio")
    private UUID hotelId;
}
