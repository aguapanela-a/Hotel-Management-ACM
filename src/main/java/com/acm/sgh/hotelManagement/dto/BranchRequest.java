package com.acm.sgh.hotelManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BranchRequest {

    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String branchName;
}
