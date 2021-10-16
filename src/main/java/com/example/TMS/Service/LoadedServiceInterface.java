package com.example.TMS.Service;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.DTO.LoadedDTO;

public interface LoadedServiceInterface {
    BaseResponse create(LoadedDTO loadedDTO);
    BaseResponse getbyid(long id);
    BaseResponse deletebyid(long id);
    BaseResponse putupdate(LoadedDTO loadedDTO, Long id);
}
