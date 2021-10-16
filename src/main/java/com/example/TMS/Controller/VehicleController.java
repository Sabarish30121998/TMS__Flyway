package com.example.TMS.Controller;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.BaseResponse.PaginationResponse;
import com.example.TMS.DTO.VehicleDTO;
import com.example.TMS.Service.VehicleServiceInterface;
import com.example.TMS.ServiceImplements.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleServiceInterface vehicleServiceInterface;

    @PostMapping("/create")
    @RolesAllowed(value = "user")
    public BaseResponse create1(@RequestBody VehicleDTO vehicleDTO)
    {
        return vehicleServiceInterface.create(vehicleDTO);
    }

    @RolesAllowed(value = "admin")
    @DeleteMapping("/deletebyid")
    public BaseResponse deletebyid1(@RequestParam long id)
    {
        return vehicleServiceInterface.deletebyid(id);
    }

    @GetMapping("/getbyid")
    @RolesAllowed(value = "user")
    public BaseResponse getbyid1(@RequestParam long id)
    {
        return vehicleServiceInterface.getbyid(id);
    }

    @RolesAllowed(value = "admin")
    @PutMapping("/putupdate")
    public BaseResponse putupdate1(@RequestParam long id,@RequestBody VehicleDTO vehicleDTO)
    {
        return vehicleServiceInterface.putupdate(id,vehicleDTO);
    }

    @RolesAllowed(value = "admin")
    @GetMapping("/pagination/{currpagenumber}/{totalnumberofrecordsinpage}/{registrationnumber}")
    public PaginationResponse pagination1(@PathVariable int currpagenumber,@PathVariable int totalnumberofrecordsinpage,@PathVariable String registrationnumber )
    {
        return vehicleServiceInterface.pagination(currpagenumber,totalnumberofrecordsinpage,registrationnumber);
    }
}
