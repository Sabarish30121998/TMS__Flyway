package com.example.TMS.Repository;

import com.example.TMS.EntityORModel.Loaded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadedRepo extends JpaRepository<Loaded,Long> {
}
