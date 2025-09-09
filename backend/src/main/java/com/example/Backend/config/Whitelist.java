package com.example.Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Whitelist {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/signin", "/signup")
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signin", "/signup", "/csrf-token", "/access").permitAll() // whitelist
                        .anyRequest().authenticated() // everything else requires auth
                );

        return http.build();
    }
}
