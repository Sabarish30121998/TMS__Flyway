package com.example.TMS.ServiceImplements;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.BaseResponse.PaginationResponse;
import com.example.TMS.DTO.LoginResponseDTO;
import com.example.TMS.DTO.RoleDTO;
import com.example.TMS.DTO.TokenDTO;
import com.example.TMS.DTO.UserDTO;
import com.example.TMS.EntityORModel.Role;
import com.example.TMS.EntityORModel.User;
import com.example.TMS.EntityORModel.UserRole;
import com.example.TMS.Repository.RoleRepo;
import com.example.TMS.Repository.UserRepo;
import com.example.TMS.Repository.UserRoleRepo;
import com.example.TMS.Service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import java.util.*;

import static com.example.TMS.Utils.JwtUtil.generateToken;

@Service

public class UserService implements UserServiceInterface {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserRoleRepo userRoleRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public BaseResponse create(UserDTO userDTO) {
        BaseResponse baseResponse=new BaseResponse();
        User user = new User();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        user.setUserName(userDTO.getUserName());
        user.setUserType(userDTO.getUserType());
        user.setPassword(bcrypt.encode(userDTO.getPassword()));
        user.setCity(userDTO.getCity());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setIsDeleted(0);
        user.setIsActive(0);
       // user.setUserRole(userDTO.getUserRole());

        List<Role> list = new LinkedList<>();
        userDTO.getUserRole().stream().forEachOrdered(roleDTO -> {
            Role role = new Role();
            role.setRoleName(roleDTO.getRoleName());
            list.add(role);
        });

        user.setListOfRole(list);

       user=userRepo.save(user);
       // saveRole(userDTO.getUserRole(),user);

        baseResponse.setStatuscode("200");
        baseResponse.setStatusmessage("successfully value created");
        baseResponse.setData(user);
        return baseResponse;
    }


   /* private void saveRole(List<RoleDTO> roles, User userDetail) {
        try {
            List<UserRole> userRoles = new LinkedList<>();
            if (Objects.nonNull(roles) && roles.size() > 0) {
                roles.stream().forEachOrdered(role -> {
                    Role role1 = roleRepo.findById(role.getRoleId())
                            .orElseThrow(() -> new RuntimeException("role is not here"));
                    UserRole userRole = new UserRole();
                    userRole.setUser(userDetail);
                    userRole.setRole(role1);
                    userRoles.add(userRole);
                });
                userRoleRepo.saveAll(userRoles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    @Override
    public BaseResponse getbyid(Long id)
    {
        BaseResponse baseResponse = new BaseResponse();
        Optional<User> sabari = userRepo.findById(id);
        if (sabari.isPresent()) {
            if (sabari.get().getIsDeleted() == 0) {
                baseResponse.setStatuscode("success");
                baseResponse.setStatusmessage("Data found");
                baseResponse.setData(sabari);
            } else {
                throw  new RuntimeException("This Data is deleted");
            }
        }
        else
        {
            throw  new RuntimeException("Please enter a valid User ID");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deletebyid(Long id) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<User> sabari = userRepo.findById(id);
        if(sabari.isPresent()) {
            sabari.get().setIsDeleted(1);
            userRepo.save(sabari.get());
            baseResponse.setStatuscode("200");
            baseResponse.setStatusmessage("successfully deleted");
        }
        else
        {
            throw  new RuntimeException("Please enter the valid user id to delete");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse putupdate(UserDTO userDTO,Long id) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<User> sabari = userRepo.findById(id);

        if(sabari.isPresent()) {
            sabari.get().setUserName(sabari.get().getUserName());
            sabari.get().setUserType(userDTO.getUserType());
            sabari.get().setPassword(userDTO.getPassword());
            sabari.get().setCity(userDTO.getCity());
            sabari.get().setPhoneNumber(userDTO.getPhoneNumber());
            sabari.get().setIsActive(userDTO.getIsActive());
            sabari.get().setIsDeleted(userDTO.getIsDeleted());

            userRepo.save(sabari.get());
            baseResponse.setStatuscode("200");
            baseResponse.setStatusmessage("successfully value created");
            baseResponse.setData(sabari);
        }
        else{
            throw  new RuntimeException("Enter A Valid UserId");
        }

        return baseResponse;
    }


    @Override
    public PaginationResponse pagination(int currpagenumber, int totalnumberofrecordsinpage, String username) {
    PaginationResponse paginationResponse=new PaginationResponse();
        Pageable paging = PageRequest.of(currpagenumber,totalnumberofrecordsinpage);
        Page<User> users= userRepo.searchAllByUserNameLike("%" + username +"%",paging);
        paginationResponse.setResponse(users);
        paginationResponse.setPagecount(users.getTotalPages());

    return paginationResponse;
    }

    @Autowired
    LoginResponseDTO loginResponseDTO;


    @Override
    public BaseResponse loggedin(TokenDTO tokenDTO) {
        BaseResponse baseResponse= new BaseResponse();

        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        List sabari = new LinkedList();
        try{
            Optional<User> users = userRepo.findByUserName(tokenDTO.getUserName());
            if(users.isPresent())
            {
                boolean check = bcrypt.matches(tokenDTO.getPassword(), users.get().getPassword());
                if(check == true)
                {
                   List<UserRole> userRole = userRoleRepo.findByUserOwnerId(users.get().getOwnerId());
                   userRole.stream().forEachOrdered(userRole1 ->{
                       Role role = userRole1.getRole();
                       sabari.add(role);
                   });
                }
                    String tokens= generateToken("USER",users.get().getOwnerId(),sabari,users.get().getUserName());

                    loginResponseDTO.setToken(tokens);
                    loginResponseDTO.setOwnerId(users.get().getOwnerId());
                    loginResponseDTO.setPassword(users.get().getPassword());
                    loginResponseDTO.setUserName(users.get().getUserName());
                    loginResponseDTO.setRoleList(sabari);

                    baseResponse.setStatuscode("success");
                    baseResponse.setStatusmessage("Token generated");
                    baseResponse.setData(loginResponseDTO);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return baseResponse;

    }


    public UserDetails loadByUserName(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(userName);
        List<Role> sabari = new LinkedList<>();
        if(user==null)
        {
            throw new UsernameNotFoundException("User Not Found");
        }

/*        user.get().getListOfRole().stream().forEachOrdered(role -> {
            sabari.add(role);
        });*/


        List<UserRole> userRole = userRoleRepo.findByUserOwnerId(user.get().getOwnerId());
        userRole.stream().forEachOrdered(userRole1 ->{
            Role role = userRole1.getRole();
            sabari.add(role);
        });

        return new org.springframework.security.core.userdetails.User(
                user.get().getUserName(),
                user.get().getPassword(),
                getAuthority(sabari)
        );
    }
    private List getAuthority(List<Role> roles)
    {
        List authorities = new ArrayList();
        roles.stream().forEachOrdered(userRole1 -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole1.getRoleName()));
        });
        return authorities;
    }



}
