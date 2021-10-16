package com.example.TMS.Service;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.BaseResponse.PaginationResponse;
import com.example.TMS.DTO.VehicleDTO;

public interface VehicleServiceInterface {
    BaseResponse create(VehicleDTO vehicleDTO);
    BaseResponse deletebyid(long id);
    BaseResponse getbyid(long id);
    BaseResponse putupdate(long id, VehicleDTO vehicleDTO);
    PaginationResponse pagination(int currpagenumber, int totalnumberofrecordsinpage, String registrationnumber);

}
