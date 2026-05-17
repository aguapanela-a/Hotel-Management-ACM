package com.acm.sgh.auth.dto;

import com.acm.sgh.auth.enumeration.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EmployeeResponse {
    private UUID id;
    private UserResponse user;
    private Role role;
}
