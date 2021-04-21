package com.alphacoder.springsecuritycustomauthenticationprovider.security;

import antlr.StringUtils;
import com.alphacoder.springsecuritycustomauthenticationprovider.security.model.User;
import com.alphacoder.springsecuritycustomauthenticationprovider.security.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor(onConstructor = @__ ({@Autowired}))
public class CustomAuthorizationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getName();
        String password= String.valueOf(authentication.getCredentials());
        User user= this.userService.loadUserByUsername(username);

        if(this.passwordEncoder.matches(password, user.getPassword())){
            var authObj= new UsernamePasswordAuthenticationToken
                    (username, password, user.getAuthorities());
            return authObj;
        }

        throw new BadCredentialsException("Error");
    }

    @Override
    public boolean supports(Class<?> authType) {
        return UsernamePasswordAuthenticationToken.class.equals(authType);
    }
}
