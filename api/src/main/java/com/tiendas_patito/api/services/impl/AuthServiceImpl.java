package com.tiendas_patito.api.services.impl;

import com.tiendas_patito.api.entities.RoleEntity;
import com.tiendas_patito.api.entities.UserEntity;
import com.tiendas_patito.api.models.AuthResponse;
import com.tiendas_patito.api.models.ERole;
import com.tiendas_patito.api.models.LoginUserRequest;
import com.tiendas_patito.api.models.RegisterUserRequest;
import com.tiendas_patito.api.repositories.IUserRepository;
import com.tiendas_patito.api.services.IAuthService;
import com.tiendas_patito.api.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginUserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserEntity userEntity = iUserRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtUtils.generateAccesToken(new HashMap<>(), userEntity.getUsername());
        return AuthResponse.builder().idCliente(userEntity.getId()).token(token).build();
    }

    @Override
    public AuthResponse register(RegisterUserRequest request) {
        Set<RoleEntity> roles = request.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isEnabled(request.getIsEnabled())
                .roles(roles)
                .build();
        iUserRepository.save(userEntity);
        String token = jwtUtils.generateAccesToken(new HashMap<>(), userEntity.getUsername());
        return AuthResponse.builder().token(token).build();
    }
}
