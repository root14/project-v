package com.root14.projectv.controller;

import com.root14.projectv.Repository.UserRepository;
import com.root14.projectv.dto.UserRegisterDto;
import com.root14.projectv.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}
