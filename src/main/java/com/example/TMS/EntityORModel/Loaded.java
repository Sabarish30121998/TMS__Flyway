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
@Table(name = "loaded")
public class Loaded {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loaded_id")
    private Long loadedId;

    @Column(name = "load_name")
    private String loadName;

    @Column(name = "source_from")
    private String sourceFrom;

    @Column(name = "to_destination")
    private String toDestination;

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



    @ManyToOne
    @JoinColumn(name = "fk_vehicle_id")
    private Vehicle vehicle;

}
