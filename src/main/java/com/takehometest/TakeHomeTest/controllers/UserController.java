package com.takehometest.TakeHomeTest.controllers;

import com.takehometest.TakeHomeTest.controllers.request.LoginRequest;
import com.takehometest.TakeHomeTest.controllers.request.RegisterRequest;
import com.takehometest.TakeHomeTest.controllers.response.AuthenticationResponse;
import com.takehometest.TakeHomeTest.libraries.constants.ApiPath;
import com.takehometest.TakeHomeTest.models.User;
import com.takehometest.TakeHomeTest.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping(ApiPath.USER)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(ApiPath.REGISTER)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        String jwtToken = userService.signUp(user);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping(ApiPath.LOGIN)
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest loginRequest
    ) throws AuthenticationException {
        User user = new User();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());
        String jwtToken = userService.login(user);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        return ResponseEntity.ok(authenticationResponse);
    }
}
