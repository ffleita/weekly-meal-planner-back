package ffleitas.back.service;

import ffleitas.back.domain.entities.User;
import ffleitas.back.dtos.auth.LoginRequest;
import ffleitas.back.dtos.auth.RegisterRequest;
import ffleitas.back.dtos.auth.TokenResponse;


public interface AuthService
{
	TokenResponse register (RegisterRequest request);

	TokenResponse login(LoginRequest request);

	TokenResponse refresh(String authHeader);
}
