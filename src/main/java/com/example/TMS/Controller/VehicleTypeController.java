package com.example.TMS.Controller;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.DTO.VehicleTypeDTO;
import com.example.TMS.Service.VehicleTypeServiceInterface;
import com.example.TMS.ServiceImplements.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("/vehicletype")
public class VehicleTypeController {

    @Autowired
    VehicleTypeServiceInterface vehicleTypeServiceInterface;

    @PostMapping("/create")
    @RolesAllowed(value = "user")
    public BaseResponse create1(@RequestBody VehicleTypeDTO vehicleTypeDTO)
    {
        return vehicleTypeServiceInterface.create(vehicleTypeDTO);
    }

    @GetMapping("/getbyid")
    @RolesAllowed(value = "user")
    public BaseResponse getbyid1(@RequestParam long id)
    {

        return vehicleTypeServiceInterface.getbyid(id);
    }

    @DeleteMapping("/deletebyid")
    @RolesAllowed(value = "admin")
    public BaseResponse deletebyid1(@RequestParam long id)
    {
        return vehicleTypeServiceInterface.deletebyid(id);
    }

    @PutMapping("/putupdate")
    @RolesAllowed(value = "admin")
    public BaseResponse putupdate1(@RequestBody VehicleTypeDTO vehicleTypeDTO,@RequestParam Long id)
    {
        return vehicleTypeServiceInterface.putupdate(vehicleTypeDTO,id);
    }
}
