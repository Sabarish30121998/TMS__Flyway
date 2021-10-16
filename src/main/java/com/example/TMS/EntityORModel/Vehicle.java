package com.example.TMS.EntityORModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Setter
@Getter
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "is_active")
    private int isActive;

    @Column(name = "is_deleted")
    private  int isDeleted;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    @OneToOne
    @JoinColumn(name = "fk_vehicletype_id")
    private  VehicleType vehicleTypeid;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private  User user;


}
