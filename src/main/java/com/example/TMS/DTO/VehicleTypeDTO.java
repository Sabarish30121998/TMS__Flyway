package com.example.TMS.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class VehicleTypeDTO {
    private Long vehicleTypeId;
    private String vehicleTypeName;
    private int isActive;
    private  int isDeleted;
}
