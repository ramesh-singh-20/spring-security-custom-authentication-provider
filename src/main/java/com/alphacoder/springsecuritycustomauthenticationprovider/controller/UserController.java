package com.alphacoder.springsecuritycustomauthenticationprovider.controller;

import com.alphacoder.springsecuritycustomauthenticationprovider.entity.UserEntity;
import com.alphacoder.springsecuritycustomauthenticationprovider.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserController {
    private final UserService userService;

    @GetMapping(value="/hello")
    public String sayHello(){
        return "Hello!!!";
    }

    @PostMapping(value="/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserEntity userEntity){
        this.userService.createUser(userEntity);
    }
}
