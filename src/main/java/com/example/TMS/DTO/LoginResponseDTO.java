package com.example.TMS.DTO;

import com.example.TMS.EntityORModel.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Getter
@Setter
@Component
public class LoginResponseDTO {

    private String token;
    private String userName;
    private String password;

    private Long ownerId;
    private List<UserRole> roleList;


}
