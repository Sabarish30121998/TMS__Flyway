package com.example.TMS.EntityORModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Setter
@Getter
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    @Column(name = "role_name")
    private String roleName;
}
