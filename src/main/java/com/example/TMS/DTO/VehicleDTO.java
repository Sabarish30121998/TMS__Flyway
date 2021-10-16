package com.example.TMS.DTO;

import com.example.TMS.EntityORModel.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class VehicleDTO {
    private Long vehicleId;
    private String vehicleName;
    private String registrationNumber;
    private int isActive;
    private  int isDeleted;

    //one to one mapping
    private long vehicleTypeDTOidmapping;

    //one to many mapping
    private List<User> userDTOidmapping;


   // private List<LoadedDTO> loadedidDTOmapping;

}
