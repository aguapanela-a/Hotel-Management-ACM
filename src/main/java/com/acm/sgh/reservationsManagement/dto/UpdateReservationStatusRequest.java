package com.acm.sgh.reservationsManagement.dto;

import com.acm.sgh.reservationsManagement.enumerations.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateReservationStatusRequest {

    @NotNull(message = "El estado es obligatorio")
    private ReservationStatus status;
}
