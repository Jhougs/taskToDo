package com.juanviana.app.todoapp.juanviana_todoapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests( (authz) -> authz
        .requestMatchers(HttpMethod.GET, "/tasks").permitAll()
        .requestMatchers(HttpMethod.GET, "auth/authorized").permitAll()
        .requestMatchers(HttpMethod.GET,"auth/list").hasAnyAuthority("SCOPE_read", "SCOPE_write")
        .requestMatchers(HttpMethod.POST,"auth/create").hasAuthority("SCOPE_write")
        .anyRequest().authenticated())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //La sesion Http del usuario se maneja en el token y no en la peticion http
        .oauth2Login( login -> login.loginPage("/oauth2/authorization/client-todoapp"))
        .oauth2Client(withDefaults())
        .oauth2ResourceServer(resourceServer -> resourceServer.jwt(withDefaults()));

        return http.build();
            
    
        

    }

}
