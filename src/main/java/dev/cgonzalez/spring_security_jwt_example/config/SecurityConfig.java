package dev.cgonzalez.spring_security_jwt_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // The way to configure security is by exposing a Bean of the SecurityFilterChain type.
    // This occurs with Spring Security 6 (Spring Boot 3)
    // Spring automatically detects this bean and uses the configuration it contains to build the entire chain of security filters.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                // CSRF is disabled because the application is an API and does not use sessions or cookies
                .csrf(AbstractHttpConfigurer::disable)

                // Every request must be authenticated
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

                // The API is stateless: Spring will not create or use HttpSession
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Enable HTTP Basic authentication (only for testing; will be replaced by JWT)
                .httpBasic(Customizer.withDefaults())

                .build();
    }

    @Bean
    public InMemoryUserDetailsManager users(){
        return new InMemoryUserDetailsManager(
                User.withUsername("cristhian")
                        // {noop} indicates that the password is not encoded; for tests only
                        .password("{noop}password")
                        .authorities("read")
                        .build()
        );
    }
}