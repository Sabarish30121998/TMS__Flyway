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
@Table(name = "vehicletype")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicletype_id")
    private Long vehicleTypeId;

    @Column(name = "vehicletype_name")
    private String vehicleTypeName;

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
}
