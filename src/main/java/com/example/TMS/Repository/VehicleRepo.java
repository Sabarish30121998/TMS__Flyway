package com.example.TMS.Repository;

import com.example.TMS.EntityORModel.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle,Long> {
    Page<Vehicle> searchAllByRegistrationNumberLike(String s, Pageable paging);
}
