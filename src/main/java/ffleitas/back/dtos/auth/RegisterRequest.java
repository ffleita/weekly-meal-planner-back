package ffleitas.back.dtos.auth;

import java.util.List;


public record RegisterRequest(
		String email,
		String password,
		String username,
		String role
) {}
