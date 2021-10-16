package com.example.TMS.Controller;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.BaseResponse.PaginationResponse;
import com.example.TMS.DTO.TokenDTO;
import com.example.TMS.DTO.UserDTO;
import com.example.TMS.Service.UserServiceInterface;
import com.example.TMS.ServiceImplements.UserService;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserServiceInterface userServiceInterface;

   @PostMapping("/create")
    public BaseResponse create1(@RequestBody UserDTO userDTO)
   {
       return userServiceInterface.create(userDTO);
   }



    @GetMapping("/getbyid")
    @RolesAllowed(value = "user")
    public BaseResponse getbyid1(@RequestParam Long id)
   {
       return userServiceInterface.getbyid(id);
   }


   @DeleteMapping("/deletebyid")
   @RolesAllowed(value = "admin")
    public BaseResponse deletebyid1(@RequestParam Long id)
   {
       return userServiceInterface.deletebyid(id);
   }


   @PutMapping("/putupdate")
   @RolesAllowed(value = "admin")
    public BaseResponse update1(@RequestBody UserDTO userDTO,@RequestParam Long id)
   {
       return userServiceInterface.putupdate(userDTO,id);
   }



   @GetMapping("/pagination/{currpagenumber}/{totalnumberofrecordsinpage}/{username}")
   @RolesAllowed(value = "admin")
    public PaginationResponse pagination1(@PathVariable int currpagenumber, @PathVariable int totalnumberofrecordsinpage, @PathVariable String username)
   {
       return  userServiceInterface.pagination(currpagenumber,totalnumberofrecordsinpage,username);
   }



    @GetMapping("/loggedin")
    public BaseResponse logingin(@RequestBody TokenDTO tokenDTO)
    {
        return userServiceInterface.loggedin(tokenDTO);
    }

}
