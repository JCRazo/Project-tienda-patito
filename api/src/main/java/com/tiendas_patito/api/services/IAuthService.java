package com.tiendas_patito.api.services;

import com.tiendas_patito.api.models.AuthResponse;
import com.tiendas_patito.api.models.LoginUserRequest;
import com.tiendas_patito.api.models.RegisterUserRequest;

public interface IAuthService {

    public AuthResponse login(LoginUserRequest request);

    public AuthResponse register(RegisterUserRequest request);
}
