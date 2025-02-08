package com.dipartimento.projecthotelnew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //consente di richieste CORS su tutti i percorsi
                        .allowedOrigins("http://localhost:4200") //permtte richieste solo da questo dominio
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //metodi consentiti
                        .allowedHeaders("*") //
                        .allowCredentials(true); //consente di includere cookie/autenticazione
            }
        };
    }
}
