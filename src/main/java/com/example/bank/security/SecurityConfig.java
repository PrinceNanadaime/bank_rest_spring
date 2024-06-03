package com.example.bank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Отключение CSRF защиты, чтобы не выдавало ошибки доступа 403 при попытке послать запрос на изменение
        http.csrf(AbstractHttpConfigurer::disable);
        //Этот код конфигурации Spring Security разрешает доступ ко всем эндпоинтам без требования аутентификации

        
        http.authorizeHttpRequests((authorize) -> authorize
                .anyRequest().permitAll());
        return http.build();
    }
}
