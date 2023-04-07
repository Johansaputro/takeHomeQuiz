package com.takehometest.TakeHomeTest.libraries.constants.unittest;

import com.takehometest.TakeHomeTest.controllers.request.LoginRequest;
import com.takehometest.TakeHomeTest.controllers.request.RegisterRequest;

public class UserControllerTestVariable {
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "pasword";
    protected static final String JWT_TOKEN = "jwtToken";
    protected static RegisterRequest createRegisterRequest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(USERNAME);
        registerRequest.setPassword(PASSWORD);
        return registerRequest;
    }
    protected static LoginRequest createLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(USERNAME);
        loginRequest.setPassword(PASSWORD);
        return loginRequest;
    }
    protected static RegisterRequest REGISTER_REQUEST = createRegisterRequest();
    protected static LoginRequest LOGIN_REQUEST = createLoginRequest();
}
