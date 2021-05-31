package com.trunk.userservice.controller;

import com.trunk.userservice.model.UserModel;
import com.trunk.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity createUser(HttpServletRequest request, HttpServletResponse response,
                                                @Valid @NotNull @ModelAttribute("name") String name,
                                                @Valid @NotNull @ModelAttribute("phone") String phone,
                                                @Valid @NotNull @ModelAttribute("email") String email,
                                                @Valid @NotNull @ModelAttribute("password") String password,
                                                @Valid @NotNull @ModelAttribute("address") String address,
                                                @Valid @NotNull @ModelAttribute("birthdate") Date birthdate){

        UserModel userModel = new UserModel();
        userModel.setUserName(name);
        userModel.setUserPhone(phone);
        userModel.setUserEmail(email);
        userModel.setUserPassword(password);
        userModel.setUserAddress(address);
        userModel.setUserBirthDate(birthdate);
        try{
            UserModel userSave = userService.createUser(userModel);
            Map map = new HashMap();
            map.put("code", HttpServletResponse.SC_CREATED);
            map .put("status", "SUCCESS");
            map.put("message", userSave.getUserName() + " CREATED");
            return ResponseEntity.status(HttpStatus.CREATED).body(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
