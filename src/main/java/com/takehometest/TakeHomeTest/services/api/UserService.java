package com.takehometest.TakeHomeTest.services.api;

import com.takehometest.TakeHomeTest.controllers.response.AuthenticationResponse;
import com.takehometest.TakeHomeTest.models.User;

import javax.naming.AuthenticationException;

public interface UserService {
    public String signUp(User user);
    public String login(User user) throws AuthenticationException;
}
