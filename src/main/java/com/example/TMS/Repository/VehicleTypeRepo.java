package com.example.TMS.Repository;

import com.example.TMS.EntityORModel.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepo extends JpaRepository<VehicleType, Long> {
}
