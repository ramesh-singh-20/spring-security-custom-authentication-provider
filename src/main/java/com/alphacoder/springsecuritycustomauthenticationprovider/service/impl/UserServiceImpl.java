package com.alphacoder.springsecuritycustomauthenticationprovider.service.impl;


import com.alphacoder.springsecuritycustomauthenticationprovider.entity.UserEntity;
import com.alphacoder.springsecuritycustomauthenticationprovider.repository.UserRepository;
import com.alphacoder.springsecuritycustomauthenticationprovider.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void createUser(UserEntity userEntity) {
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        this.userRepository.save(userEntity);
    }
}
