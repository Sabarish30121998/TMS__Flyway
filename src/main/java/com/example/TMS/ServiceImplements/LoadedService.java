package com.example.TMS.ServiceImplements;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.DTO.LoadedDTO;
import com.example.TMS.EntityORModel.Loaded;
import com.example.TMS.EntityORModel.Vehicle;
import com.example.TMS.Repository.LoadedRepo;
import com.example.TMS.Repository.VehicleRepo;
import com.example.TMS.Service.LoadedServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoadedService implements LoadedServiceInterface {
    @Autowired
    LoadedRepo loadedRepo;

    @Autowired
    VehicleRepo vehicleRepo;

    @Override
    public BaseResponse create(LoadedDTO loadedDTO) {
        BaseResponse baseResponse = new BaseResponse();

        Loaded loaded = new Loaded();
        loaded.setLoadName(loadedDTO.getLoadName());
        loaded.setSourceFrom(loadedDTO.getSourceFrom());
        loaded.setToDestination(loadedDTO.getToDestination());
        loaded.setIsDeleted(0);
        loaded.setIsActive(0);

        loadedDTO.getVehicleidDTOMapping().forEach(vehicle -> {
            Optional<Vehicle> sabari=vehicleRepo.findById(vehicle.getVehicleId());
            if(sabari.isPresent())
            {
                loaded.setVehicle(sabari.get());
                Loaded obj= loadedRepo.save(loaded);
            }
            else
            {
                throw  new RuntimeException("Please enter a valid vehicle ID");
            }

        });



        baseResponse.setStatuscode("success");
        baseResponse.setStatusmessage("Loaded data is successfully created");
        baseResponse.setData(loaded);
        return baseResponse;

    }

    @Override
    public BaseResponse getbyid(long id) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<Loaded> sabari = loadedRepo.findById(id);
        if(sabari.isPresent())
        {
            if(sabari.get().getIsDeleted()==0)
            {
                baseResponse.setStatuscode("success");
                baseResponse.setStatusmessage("Requested loaded id successfully getted");
                baseResponse.setData(sabari);
            }
            else
            {
                throw  new RuntimeException("The Requested LoadedID is Deleted");
            }

        }
        else
        {
            throw  new RuntimeException("Please give a valid Loaded ID");
        }

        return  baseResponse;
    }

    @Override
    public BaseResponse deletebyid(long id) {
        BaseResponse baseResponse= new BaseResponse();
        Optional<Loaded> sabari = loadedRepo.findById(id);
        if(sabari.isPresent()) {
            sabari.get().setIsDeleted(1);
            loadedRepo.save(sabari.get());
            baseResponse.setStatuscode("success");
            baseResponse.setStatusmessage("This Loaded ID is successfully deleted");
        }
        else
        {
            throw  new RuntimeException("Please enter the valid loaded id to delete");
        }
        return  baseResponse;
    }

    @Override
    public BaseResponse putupdate(LoadedDTO loadedDTO, Long id) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<Loaded> sabari = loadedRepo.findById(id);
        if(sabari.isPresent())
        {
            sabari.get().setLoadName(loadedDTO.getLoadName());
            sabari.get().setSourceFrom(loadedDTO.getSourceFrom());
            sabari.get().setToDestination(loadedDTO.getToDestination());
            sabari.get().setIsDeleted(loadedDTO.getIsDeleted());
            sabari.get().setIsActive(loadedDTO.getIsActive());

            loadedRepo.save(sabari.get());

            baseResponse.setStatuscode("success");
            baseResponse.setStatusmessage("This Loaded Id is Successfully Updated");
            baseResponse.setData(sabari.get());

        }
        else
        {
            throw  new RuntimeException("Please enter a valid Loaded id");
        }


        return baseResponse;
    }

  /* public BaseResponse create(VehicleDTO vehicleDTO) {

        BaseResponse baseResponse= new BaseResponse();

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        vehicle.setIsActive(0);
        vehicle.setIsDeleted(0);

        vehicleRepo.save(vehicle);

        vehicleDTO.getLoadedidDTOmapping().stream().forEachOrdered(loadedDTO -> {
            Loaded loaded = new Loaded();
            loaded.setLoadName(loadedDTO.getLoadName());
            loaded.setSourceFrom(loadedDTO.getSourceFrom());
            loaded.setToDestination(loadedDTO.getToDestination());
            loaded.setIsActive(0);
            loaded.setIsDeleted(0);

            loadedRepo.save(loaded);

        });

        baseResponse.setStatuscode("Success");
        baseResponse.setStatusmessage("Loaded ID is Created");
        baseResponse.setData(vehicle);
        return  baseResponse;
    }*/
}
