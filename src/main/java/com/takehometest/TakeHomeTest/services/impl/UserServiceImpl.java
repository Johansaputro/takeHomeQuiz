package com.takehometest.TakeHomeTest.services.impl;

import com.takehometest.TakeHomeTest.config.JwtTokenUtil;
import com.takehometest.TakeHomeTest.models.User;
import com.takehometest.TakeHomeTest.repositories.UserRepository;
import com.takehometest.TakeHomeTest.services.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String signUp(User user) {
        LOGGER.info("User sign up with details {}", user);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);

        return this.jwtTokenUtil.generateToken(user);
    }

    @Override
    public String login(User user) {
        LOGGER.info("User log in with details {}", user);

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            User existUser = this.userRepository.findByUsername(user.getUsername());
            String jwtToken = this.jwtTokenUtil.generateToken(existUser);

            return jwtToken;
        } catch (Exception e) {
            LOGGER.error("Error in Login {}", e.getMessage(), e);
            throw new RuntimeException("Error during login");
        }
    }
}
