package com.acm.sgh.auth.dto;

import com.acm.sgh.auth.enumeration.UserType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String username;
    private UserType userType;
}
