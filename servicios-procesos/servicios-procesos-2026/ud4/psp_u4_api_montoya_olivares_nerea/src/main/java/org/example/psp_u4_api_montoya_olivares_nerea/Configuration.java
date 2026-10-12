package org.example.psp_u4_api_montoya_olivares_nerea;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Activa los servicios REST en el servidor.
 * Establece "api" como ruta base para las consultas, inserciones, modificaciones y eliminaciones.
 */

@ApplicationPath("api")
public class Configuration extends Application {}
