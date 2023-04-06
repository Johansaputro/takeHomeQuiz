package com.takehometest.TakeHomeTest.libraries.constants;

public class ApiPath {
    public static final String BASE_PATH = "/microservice-name";
//    user controller
    public static final String USER = BASE_PATH + "/user";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";

//    job controller
    public static final String JOB = BASE_PATH + "/job";
    public static final String POSITION = "/positions";
    public static final String DETAILS = "/details";
    public static final String DOWNLOAD = "/download";

//    Outbound api
    public static final String OUTBOUND_POSITIONS = "positions.json";
    public static final String OUTBOUND_DETAIL = "positions/";
}
