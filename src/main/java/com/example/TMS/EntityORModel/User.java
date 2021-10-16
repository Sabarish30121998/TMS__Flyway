package com.example.TMS.EntityORModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "user_name")
    private String  userName;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "phone_number")
    private Long phoneNumber;


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



    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "userrole",
            joinColumns = {@JoinColumn(name="fk_owner_id",referencedColumnName =  "owner_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_role_id",referencedColumnName = "role_id")}
    )
    private List<Role> listOfRole;


    public User(User user) {
        this.userName=userName;
        this.password=password;
    }

    public User() {

    }
}
