package com.takehometest.TakeHomeTest.services;

import com.takehometest.TakeHomeTest.config.JwtTokenUtil;
import com.takehometest.TakeHomeTest.libraries.constants.unittest.UserServiceTestVariable;
import com.takehometest.TakeHomeTest.models.User;
import com.takehometest.TakeHomeTest.repositories.UserRepository;
import com.takehometest.TakeHomeTest.services.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceImplTest extends UserServiceTestVariable {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtTokenUtil jwtTokenUtil;

    @Mock
    AuthenticationManager authenticationManager;

    @Before
    public void setup() {
        initMocks(this);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(userRepository, passwordEncoder, jwtTokenUtil);
    }

    @Test
    public void signUpTest() {
        Mockito.when(this.passwordEncoder.encode(anyString())).thenReturn(ENCODED_PASS);
        Mockito.when(this.userRepository.save(any(User.class))).thenReturn(SAVED_USER);
        Mockito.when(this.jwtTokenUtil.generateToken(SAVED_USER)).thenReturn(TOKEN);

        this.userService.signUp(USER);

        Mockito.verify(this.passwordEncoder).encode(anyString());
        Mockito.verify(this.userRepository).save(any(User.class));
        Mockito.verify(this.jwtTokenUtil).generateToken(SAVED_USER);
    }

    @Test
    public void loginTest() {
        Mockito.when(this.authenticationManager.authenticate(any())).thenReturn(null);
        Mockito.when(this.userRepository.findByUsername(any())).thenReturn(SAVED_USER);
        Mockito.when(this.jwtTokenUtil.generateToken(SAVED_USER)).thenReturn(TOKEN);

        this.userService.login(USER);

        Mockito.verify(this.authenticationManager).authenticate(any());
        Mockito.verify(this.userRepository).findByUsername(any());
        Mockito.verify(this.jwtTokenUtil).generateToken(SAVED_USER);
    }

    @Test
    public void loginTestException() {
        Mockito.when(authenticationManager.authenticate(any())).thenThrow(new AuthenticationException("") {});

        try {
            this.userService.login(USER);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Error during login");
        }

        Mockito.verify(this.authenticationManager).authenticate(any());
    }
}
