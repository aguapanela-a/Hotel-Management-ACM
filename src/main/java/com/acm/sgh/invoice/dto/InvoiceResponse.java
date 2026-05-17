package com.acm.sgh.invoice.dto;

import com.acm.sgh.paymentManagement.enumeration.PaymentType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class InvoiceResponse {
    private UUID id;
    private String invoiceNumber;
    private UUID paymentId;
    private UUID reservationId;
    private PaymentType paymentType;
    private BigDecimal total;
    private LocalDateTime issueDate;
    private String customerUsername;
}
