package com.trunk.userservice.service;

import com.trunk.userservice.model.UserModel;
import com.trunk.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel createUser(UserModel userModel){
        userModel.setUserPassword(bCryptPasswordEncoder.encode(userModel.getUserPassword()));
        return userRepository.save(userModel);
    }
}
