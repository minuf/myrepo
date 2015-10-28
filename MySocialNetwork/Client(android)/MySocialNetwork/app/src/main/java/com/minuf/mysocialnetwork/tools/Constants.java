package com.minuf.mysocialnetwork.tools;

/**
 * Created by jorge on 26/10/15.
 */
public class Constants {
    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "5000";

    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "http://192.168.0.154:"; //my pc ip

    public static String DEVICE_ID = "";
    public static String TOKEN = null;
    public static String EMAIL = null;
    /**
     * URLs del Web Service (end points)
     */
    public static final String REGISTER = IP + PUERTO_HOST + "/register";
    public static final String LOGIN = IP + PUERTO_HOST + "/login";
    public static final String LOGOUT = IP + PUERTO_HOST + "/logout";
    public static final String GET_USER_BY_EMAIL = IP + PUERTO_HOST + "/users";
    public static final String DELETE_USER = IP + PUERTO_HOST + "/users/";
    public static final String UPDATE_USER = IP + PUERTO_HOST + "/users";
    public static final String GET_CONTACTS = IP + PUERTO_HOST + "/contacts";
    public static final String ADD_CONTACT = IP + PUERTO_HOST + "/contacts";
    public static final String DELETE_CONTACT = IP + PUERTO_HOST + "/contacts";
    public static final String GET_POSTS = IP + PUERTO_HOST + "/posts";
    public static final String GET_POST = IP + PUERTO_HOST + "/post";
    public static final String ADD_POST = IP + PUERTO_HOST + "/post";
    public static final String DELETE_POST = IP + PUERTO_HOST + "/post";
}
