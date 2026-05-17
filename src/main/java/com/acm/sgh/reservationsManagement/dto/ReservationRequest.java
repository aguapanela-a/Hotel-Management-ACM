package com.acm.sgh.reservationsManagement.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReservationRequest {

    @NotNull(message = "El ID de la habitación es obligatorio")
    private UUID roomId;

    @NotNull(message = "El ID del cliente es obligatorio")
    private UUID customerId;

    private UUID serviceId;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime startDate;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin debe ser futura")
    private LocalDateTime endDate;
}
