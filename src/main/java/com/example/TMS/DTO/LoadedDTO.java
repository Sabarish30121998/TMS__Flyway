package com.example.TMS.DTO;

import com.example.TMS.EntityORModel.Vehicle;
import com.example.TMS.EntityORModel.VehicleType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class LoadedDTO {
    private String loadName;
    private String sourceFrom;
    private String toDestination;
    private int isActive;
    private  int isDeleted;

    private List<Vehicle> vehicleidDTOMapping;

}
