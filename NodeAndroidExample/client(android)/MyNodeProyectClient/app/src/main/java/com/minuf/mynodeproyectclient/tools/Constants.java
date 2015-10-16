package com.minuf.mynodeproyectclient.tools;

/**
 * Created by jorge on 15/10/15.
 */
public class Constants {

    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;

    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "5000";

    /**
     * Dirección IP de genymotion o AVD
     */
    private static final String IP = "http://192.168.0.154:";
    /**
     * URLs del Web Service
     */
    public static final String GET = IP + PUERTO_HOST + "/users";
    public static final String GET_BY_ID = IP + PUERTO_HOST + "/users";
    public static final String GET_BY_EMAIL = IP + PUERTO_HOST + "/users/getbyemail";
    public static final String UPDATE = IP + PUERTO_HOST + "/users";
    public static final String DELETE = IP + PUERTO_HOST + "/users";
    public static final String INSERT = IP + PUERTO_HOST + "/users";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";
}
