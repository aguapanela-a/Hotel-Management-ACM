package com.acm.sgh.paymentManagement.dto;

import com.acm.sgh.paymentManagement.enumeration.PaymentType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentRequest {

    @NotNull(message = "El ID de la reserva es obligatorio")
    private UUID reservationId;

    @NotNull(message = "El tipo de pago es obligatorio")
    private PaymentType paymentType;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal amount;
}
