package com.example.TMS.EntityORModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name = "userrole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private long userRoleId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "fk_owner_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_role_id")
    private Role role;
}
