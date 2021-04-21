package com.alphacoder.springsecuritycustomauthenticationprovider.security.service;

import com.alphacoder.springsecuritycustomauthenticationprovider.entity.UserEntity;
import com.alphacoder.springsecuritycustomauthenticationprovider.repository.UserRepository;
import com.alphacoder.springsecuritycustomauthenticationprovider.security.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional= this.userRepository.findUserByUsername(username);

        UserEntity userEntity= userEntityOptional.
                orElseThrow(()->new UsernameNotFoundException("User not found with the provided username."));

        User user= new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());

        return user;
    }
}
