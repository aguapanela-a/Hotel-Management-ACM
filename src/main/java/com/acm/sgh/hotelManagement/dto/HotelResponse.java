package com.acm.sgh.hotelManagement.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class HotelResponse {
    private UUID id;
    private String hotelBranch;
    private String hotelName;
    private String hotelAddress;
    private String hotelCity;
    private String hotelPhone;
    private String hotelEmail;
    private String hotelCategory;
    private String hotelStatus;
}
