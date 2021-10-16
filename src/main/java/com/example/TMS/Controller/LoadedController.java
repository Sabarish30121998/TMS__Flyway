package com.example.TMS.Controller;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.DTO.LoadedDTO;
import com.example.TMS.Service.LoadedServiceInterface;
import com.example.TMS.ServiceImplements.LoadedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/loaded")
public class LoadedController {

    @Autowired
    LoadedServiceInterface loadedServiceInterface;


    @PostMapping("/create")
    @RolesAllowed(value = "user")
    public BaseResponse create (@RequestBody LoadedDTO loadedDTO)
    {
        return  loadedServiceInterface.create(loadedDTO);
    }

    @GetMapping("/getbyid")
    @RolesAllowed(value = "user")
    public BaseResponse getbyid(@RequestParam long id)
    {
        return loadedServiceInterface.getbyid(id);
    }

    @RolesAllowed(value = "admin")
    @DeleteMapping("/deletebyid")
    public BaseResponse deletebyid(@RequestParam long id)
    {
        return loadedServiceInterface.deletebyid(id);
    }

    @RolesAllowed(value = "admin")
    @PutMapping("/putupdate")
    public BaseResponse putupdate(@RequestBody LoadedDTO loadedDTO,@RequestParam Long id)
    {
        return loadedServiceInterface.putupdate(loadedDTO,id);
    }


}
