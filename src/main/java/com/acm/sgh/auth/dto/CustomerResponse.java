package com.acm.sgh.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerResponse {
    private UUID id;
    private UserResponse user;
}
