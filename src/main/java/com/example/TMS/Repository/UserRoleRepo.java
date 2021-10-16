package com.example.TMS.Repository;

import com.example.TMS.EntityORModel.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

   // List<UserRole> findByUserRoleId(Long ownerId);

    List<UserRole> findByUserOwnerId(Long ownerId);

    List<UserRole> findByUser(Long ownerId);
}
