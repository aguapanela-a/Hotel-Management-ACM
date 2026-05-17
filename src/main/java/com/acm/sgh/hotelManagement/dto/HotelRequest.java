package com.acm.sgh.hotelManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelRequest {

    private String hotelBranch;

    @NotBlank(message = "El nombre del hotel es obligatorio")
    private String hotelName;

    @NotBlank(message = "La dirección es obligatoria")
    private String hotelAddress;

    @NotBlank(message = "La ciudad es obligatoria")
    private String hotelCity;

    private String hotelPhone;

    @Email(message = "El correo no tiene un formato válido")
    private String hotelEmail;

    private String hotelCategory;

    private String hotelStatus;
}
