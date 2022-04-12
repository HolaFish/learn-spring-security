package com.learn.security.authenticationserver.service;

import com.fxsh.learn.security.base.domain.User;
import com.learn.security.authenticationserver.dao.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void auth(User user) {
        Optional<User> o = userRepository.findByUsername(user.getUsername());
        if (o.isPresent()){
            User u = o.get();
            if(passwordEncoder.matches(user.getPassword(),u.getPassword())){
                // 验证成功
            }else{
                throw new BadCredentialsException("Bad credentials");
            }
        }else{
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
