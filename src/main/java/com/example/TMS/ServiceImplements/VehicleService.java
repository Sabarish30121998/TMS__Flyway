package com.example.TMS.ServiceImplements;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.BaseResponse.PaginationResponse;
import com.example.TMS.DTO.VehicleDTO;
import com.example.TMS.EntityORModel.User;
import com.example.TMS.EntityORModel.Vehicle;
import com.example.TMS.EntityORModel.VehicleType;
import com.example.TMS.Repository.UserRepo;
import com.example.TMS.Repository.VehicleRepo;
import com.example.TMS.Repository.VehicleTypeRepo;
import com.example.TMS.Service.VehicleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService implements VehicleServiceInterface {

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    VehicleTypeRepo vehicleTypeRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public BaseResponse create(VehicleDTO vehicleDTO) {
        BaseResponse baseResponse = new BaseResponse();
        Vehicle vehicle= new Vehicle();

        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        vehicle.setIsActive(0);
        vehicle.setIsDeleted(0);

        Optional<VehicleType> vehicleType = vehicleTypeRepo.findById(vehicleDTO.getVehicleTypeDTOidmapping());
        if(vehicleType.isPresent())
        {
            vehicle.setVehicleTypeid(vehicleType.get());
        }
        else
        {
            throw  new RuntimeException("enter a valid vehicletype id");
        }

        vehicleDTO.getUserDTOidmapping().forEach(user ->{
            Optional<User> sabari = userRepo.findById(user.getOwnerId());
            if(sabari.isPresent())
            {
                vehicle.setUser(sabari.get());

            }
            else
            {
                throw  new RuntimeException("Please enter a valid user id");
            }
            vehicleRepo.save(vehicle);
        });

        baseResponse.setStatuscode("success");
        baseResponse.setStatusmessage("Successfully Data Created");
        baseResponse.setData(vehicle);
        return baseResponse;
    }

    @Override
    public BaseResponse deletebyid(long id) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<Vehicle> sabari = vehicleRepo.findById(id);
        if(sabari.isPresent())
        {
              sabari.get().setIsDeleted(1);
              vehicleRepo.save(sabari.get());
              baseResponse.setStatuscode("success");
              baseResponse.setStatusmessage("This ID successfully deleted");
        }
        else
        {
            throw  new RuntimeException("Please Enter the Valid Vehicle id to delete");
        }

        return baseResponse;
    }

    @Override
    public BaseResponse getbyid(long id) {
        BaseResponse baseResponse= new BaseResponse();
        Optional<Vehicle> sabari = vehicleRepo.findById(id);
        if(sabari.isPresent())
        {
            if(sabari.get().getIsDeleted()==0)
            {
                baseResponse.setStatuscode("success");
                baseResponse.setStatusmessage("This Vehicle Id is successfully got");
                baseResponse.setData(sabari);
            }
            else
            {
                throw  new RuntimeException("This Vehicle Id is Deleted");
            }
        }
        else
        {
            throw  new RuntimeException(" Please enter the valid vehicle id ");
        }
        return  baseResponse;
    }

    @Override
    public BaseResponse putupdate(long id, VehicleDTO vehicleDTO) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<Vehicle> sabari= vehicleRepo.findById(id);
        if(sabari.isPresent())
        {
            sabari.get().setVehicleName(vehicleDTO.getVehicleName());
            sabari.get().setRegistrationNumber(vehicleDTO.getRegistrationNumber());
            sabari.get().setIsActive(vehicleDTO.getIsActive());
            sabari.get().setIsDeleted(vehicleDTO.getIsDeleted());

            //sabari.get().setVehicleTypeid(vehicleDTO.getVehicleTypeDTOidmapping());


            vehicleRepo.save(sabari.get());
            baseResponse.setStatuscode("success");
            baseResponse.setStatusmessage("This Vehicle Id is successfully updated");
            baseResponse.setData(sabari.get());
        }
        else
        {
            throw  new RuntimeException("please enter a valid vehicle id ");
        }

        return baseResponse;
    }

    @Override
    public PaginationResponse pagination(int currpagenumber, int totalnumberofrecordsinpage, String registrationnumber) {
        PaginationResponse paginationResponse =new PaginationResponse();
        Pageable paging = PageRequest.of(currpagenumber,totalnumberofrecordsinpage);
        Page<Vehicle> sabari = vehicleRepo.searchAllByRegistrationNumberLike( "%"+registrationnumber+"%",paging);
        paginationResponse.setResponse(sabari);
        paginationResponse.setPagecount(sabari.getTotalPages());
        return paginationResponse;

    }
}
