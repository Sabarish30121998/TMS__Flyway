package com.example.TMS.Service;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.DTO.VehicleTypeDTO;

public interface VehicleTypeServiceInterface {
    BaseResponse create(VehicleTypeDTO vehicleTypeDTO);
    BaseResponse getbyid(long id);
    BaseResponse deletebyid(long id);
    BaseResponse putupdate(VehicleTypeDTO vehicleTypeDTO, Long id);
}
