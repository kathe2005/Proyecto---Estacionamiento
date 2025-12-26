package com.backend.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        //Aplico la configuración a todas las rutas que comiencen con /api
        registry.addMapping("/api/**")
                //Permite el origen de tu aplicación Angular
                .allowedOrigins("http://localhost:4200")
                //Metodos permitidos para que el registro funcione (POST)
                .allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS")
                //Permite el uso de cabeceras
                .allowedHeaders("*");
    }
}
