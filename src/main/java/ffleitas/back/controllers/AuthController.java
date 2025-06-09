package ffleitas.back.controllers;

import ffleitas.back.domain.entities.User;
import ffleitas.back.dtos.auth.LoginRequest;
import ffleitas.back.dtos.auth.RegisterRequest;
import ffleitas.back.dtos.auth.TokenResponse;
import ffleitas.back.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthServiceImpl authService;

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> authenticate(@RequestBody final LoginRequest request) {
		final TokenResponse token = authService.login(request);
		return ResponseEntity.ok(token);
	}

	@PostMapping("/register")
	public ResponseEntity<TokenResponse> register(@RequestBody final RegisterRequest request) {
		final TokenResponse token = authService.register(request);
		return ResponseEntity.ok(token);
	}

	@PostMapping("/refresh")
	public ResponseEntity<TokenResponse> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
		final TokenResponse token = authService.refresh(authHeader);
		return ResponseEntity.ok(token);
	}

}
