package com.example.TMS.ServiceImplements;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.DTO.VehicleTypeDTO;
import com.example.TMS.EntityORModel.VehicleType;
import com.example.TMS.Repository.VehicleTypeRepo;
import com.example.TMS.Service.VehicleTypeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleTypeService implements VehicleTypeServiceInterface {
    @Autowired
    VehicleTypeRepo vehicleTypeRepo;

    @Override
    public BaseResponse create(VehicleTypeDTO vehicleTypeDTO) {
        BaseResponse baseResponse= new BaseResponse();
        VehicleType vehicleType= new VehicleType();

        vehicleType.setVehicleTypeName(vehicleTypeDTO.getVehicleTypeName());
        vehicleType.setIsActive(0);
        vehicleType.setIsDeleted(0);
        vehicleTypeRepo.save(vehicleType);
        baseResponse.setStatuscode("200");
        baseResponse.setStatusmessage("successfully created");
        baseResponse.setData(vehicleType);
        return baseResponse;
    }

    @Override
    public BaseResponse getbyid(long id) {
        BaseResponse baseResponse=new BaseResponse();

        Optional<VehicleType> sabari = vehicleTypeRepo.findById(id);

        if(sabari.isPresent())
        {
           if(sabari.get().getIsDeleted()==0)
           {
               baseResponse.setStatuscode("success");
               baseResponse.setStatusmessage("getting id");
               baseResponse.setData(sabari);
           }
           else
           {
               throw  new RuntimeException("This VehicleType ID is Deleted");
           }
        }
        else {
            throw  new RuntimeException("Enter a valid VehicleType ID");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deletebyid(long id) {
        BaseResponse baseResponse = new BaseResponse();

        Optional<VehicleType> sabarish = vehicleTypeRepo.findById(id);
        if(sabarish.isPresent())
        {
           sabarish.get().setIsDeleted(1);
           vehicleTypeRepo.save(sabarish.get());
           baseResponse.setStatuscode("200");
           baseResponse.setStatusmessage("successfully deleted");
        }
        else
        {
            throw  new RuntimeException("Please enter the valid vehicle type id to delete");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse putupdate(VehicleTypeDTO vehicleTypeDTO, Long id) {
        BaseResponse baseResponse= new BaseResponse();

        Optional<VehicleType> sabari = vehicleTypeRepo.findById(id);
        if(sabari.isPresent())
        {
            sabari.get().setVehicleTypeName(vehicleTypeDTO.getVehicleTypeName());
            sabari.get().setIsActive(vehicleTypeDTO.getIsActive());
            sabari.get().setIsDeleted(vehicleTypeDTO.getIsDeleted());

            vehicleTypeRepo.save(sabari.get());
        }
        else
        {
            throw new RuntimeException("Enter A Valid UserId");
        }

        baseResponse.setStatuscode("200");
        baseResponse.setStatusmessage("successfully data updated");
        baseResponse.setData(sabari);
        return baseResponse;
    }
}
