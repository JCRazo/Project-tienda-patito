package com.tiendas_patito.api.config;

import com.tiendas_patito.api.filters.JwtAuthenticationFilter;
import com.tiendas_patito.api.models.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    final JwtAuthenticationFilter jwtAuthenticationFilter;

    final AuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"api/productos/**").hasAuthority("ROLE_".concat(ERole.CLIENT.name()))
                                .requestMatchers(HttpMethod.POST,"api/productos/**").hasAuthority("ROLE_".concat(ERole.SELLER.name()))
                                .requestMatchers(HttpMethod.PUT,"api/productos/**").hasAuthority("ROLE_".concat(ERole.SELLER.name()))
                                .requestMatchers(HttpMethod.DELETE,"api/productos/**").hasAuthority("ROLE_".concat(ERole.SELLER.name()))
                                .requestMatchers(HttpMethod.POST,"api/pedidos").hasAuthority("ROLE_".concat(ERole.CLIENT.name()))
                                .requestMatchers(HttpMethod.POST,"api/pedidos/cliente/{cliente}").hasAuthority("ROLE_".concat(ERole.CLIENT.name()))
                                .requestMatchers(HttpMethod.GET,"api/pedidos/**").hasAuthority("ROLE_".concat(ERole.SELLER.name()))
                                .requestMatchers(HttpMethod.DELETE,"api/pedidos/{id}").hasAuthority("ROLE_".concat(ERole.SELLER.name()))
                                .requestMatchers(HttpMethod.PUT,"api/pedidos/{id}/actualizar-estatus").hasAuthority("ROLE_".concat(ERole.SELLER.name()))
                                .requestMatchers(HttpMethod.PUT,"api/pedidos/{id}/actualizar-estatus").hasAuthority("ROLE_".concat(ERole.SELLER.name()))
                                .requestMatchers("api/auditorias/**").hasAuthority("ROLE_".concat(ERole.SELLER.name()))
                                .anyRequest().hasAuthority(ERole.ADMIN.name()))
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .build();
    }
}
