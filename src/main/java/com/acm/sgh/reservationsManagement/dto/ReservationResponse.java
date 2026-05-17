package com.acm.sgh.reservationsManagement.dto;

import com.acm.sgh.reservationsManagement.enumerations.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReservationResponse {
    private UUID id;
    private UUID roomId;
    private String roomType;
    private UUID customerId;
    private String customerUsername;
    private UUID serviceId;
    private String serviceName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ReservationStatus status;
    private BigDecimal totalPrice;
}
