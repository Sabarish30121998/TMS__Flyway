package com.example.TMS.Repository;

import com.example.TMS.EntityORModel.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Page<User> searchAllByUserNameLike(String s, Pageable paging);

   // Optional<User> findByUserNameAndPassword(String userName, String password);

   Optional<User> findByUserName(String userName);

    User findAllByOwnerId(long id);

    Optional<User> getByOwnerId(long id);

    // Optional<User> findByOwnerId(String userId);

}
