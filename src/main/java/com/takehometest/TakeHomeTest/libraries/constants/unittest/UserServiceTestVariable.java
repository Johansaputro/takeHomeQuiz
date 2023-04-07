package com.takehometest.TakeHomeTest.libraries.constants.unittest;

import com.takehometest.TakeHomeTest.models.User;

public class UserServiceTestVariable {
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "password";
    protected static final String ENCODED_PASS = "encodedPass";
    protected static final String TOKEN = "token";
    private static User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
    protected static User USER = createUser(USERNAME, PASSWORD);
    protected static User SAVED_USER = createUser(USERNAME, ENCODED_PASS);
}
