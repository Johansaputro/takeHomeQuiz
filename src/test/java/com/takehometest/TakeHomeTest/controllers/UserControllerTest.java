package com.takehometest.TakeHomeTest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takehometest.TakeHomeTest.libraries.constants.ApiPath;
import com.takehometest.TakeHomeTest.libraries.constants.unittest.UserControllerTestVariable;
import com.takehometest.TakeHomeTest.models.User;
import com.takehometest.TakeHomeTest.services.api.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends UserControllerTestVariable {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testRegister() throws Exception {
        when(this.userService.signUp(any(User.class))).thenReturn(JWT_TOKEN);

        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post(ApiPath.USER + ApiPath.REGISTER)
                        .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(REGISTER_REQUEST));

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(JWT_TOKEN));

        verify(this.userService).signUp(any(User.class));
    }

    @Test
    public void testLogin() throws Exception {
        when(this.userService.login(any(User.class))).thenReturn(JWT_TOKEN);

        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post(ApiPath.USER + ApiPath.LOGIN)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LOGIN_REQUEST));

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(JWT_TOKEN));

        verify(this.userService).login(any(User.class));
    }
}
