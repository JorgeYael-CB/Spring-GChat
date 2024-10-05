package com.yael.springboot.api.gchat.gchat.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IJwtService;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true
)
public class SecurityConfig {

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    IJwtService jwtService;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests( authz -> authz
                .requestMatchers(HttpMethod.GET, "/images/**").permitAll() // ruta permitda
                .requestMatchers(HttpMethod.GET, "/api/auth/get-user/**").permitAll() // ruta permitda
                .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll() // ruta permitda
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() // ruta permitida
                .requestMatchers(HttpMethod.POST, "/api/auth/verify-account").permitAll() // ruta permitida
                .requestMatchers(HttpMethod.GET, "/api/auth/verify-code-auth/{code}").permitAll() // ruta permitida
                .requestMatchers(HttpMethod.PUT, "/api/auth/update-password-email").permitAll() // ruta permitida
                .anyRequest().authenticated() // rutas bloqueadas
            )
            .addFilter( new JwtAuthToken(authenticationManager(), jwtService) ) // primer filtro (validar datos del usuario)
            .addFilter( new JwtAuthValidationFilter(authenticationManager()) ) // segundo filtro (en caso de que expire la sesion)
            .csrf( config -> config.disable()) // solo sirve para el HTML del servidor
            .cors( c -> c.configurationSource(corsConfigurationSource())) // cors
            .sessionManagement( managment -> managment
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).build();
    }


    //* Cors
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> cors = new FilterRegistrationBean<>(
            new CorsFilter(corsConfigurationSource())
        );

        // la mas alta prioridad
        cors.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return cors;
    }

}
