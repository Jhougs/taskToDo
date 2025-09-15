package com.juanviana.app.todoapp.juanviana_todoapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests( (authz) -> authz
        .requestMatchers(HttpMethod.GET, "auth/authorized").permitAll()
        .requestMatchers(HttpMethod.GET,"auth/list").permitAll()
        .requestMatchers(HttpMethod.POST,"users/createUser").permitAll()
        .requestMatchers(HttpMethod.GET, "/tasks").hasAnyAuthority("ADMIN", "MANAGER", "USER")
        .anyRequest().authenticated())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //La sesion Http del usuario se maneja en el token y no en la peticion http
        .oauth2Login( login -> login.loginPage("/oauth2/authorization/client-todoapp"))
        .oauth2Client(withDefaults())
        .oauth2ResourceServer(resourceServer -> 
    resourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
);

        return http.build();

    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            List<String> auths = jwt.getClaimAsStringList("authorities");
            if (auths != null) {
                auths.forEach(a -> {
                System.out.println("Authority encontrada: " + a);
                authorities.add(new SimpleGrantedAuthority(a));
            });
            }
            return authorities;
        });
        return converter;
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
