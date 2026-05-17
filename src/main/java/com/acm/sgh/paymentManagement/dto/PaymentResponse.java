package com.acm.sgh.paymentManagement.dto;

import com.acm.sgh.paymentManagement.enumeration.PaymentType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PaymentResponse {
    private UUID id;
    private UUID reservationId;
    private PaymentType paymentType;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
}
