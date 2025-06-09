package ffleitas.back.service.impl;

import ffleitas.back.dtos.auth.LoginRequest;
import ffleitas.back.dtos.auth.RegisterRequest;
import ffleitas.back.dtos.auth.TokenResponse;
import ffleitas.back.domain.entities.Token;
import ffleitas.back.domain.repositories.TokenRepository;
import ffleitas.back.domain.entities.User;
import ffleitas.back.domain.repositories.UserRepository;
import ffleitas.back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtServiceImpl jwtService;
	private final AuthenticationManager authenticationManager;

	public TokenResponse register(RegisterRequest request)
	{
		if (request.role().isEmpty())
		{
			throw new IllegalArgumentException("No se especifican roles para el usuario.");
		}

		var user = User.builder().email(request.email()).username(request.username())
				.createdAt(new Date())
				.password(passwordEncoder.encode(request.password()))

				.role(request.role()).build();

		var savedUser = userRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(savedUser, jwtToken);
		return new TokenResponse(jwtToken, refreshToken, user.getUsername());
	}

	private void saveUserToken(User user, String jwtToken)
	{
		var token = Token.builder()
				.user(user)
				.token(jwtToken)
				.tokenType(Token.TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.build();
		tokenRepository.save(token);
	}

	public TokenResponse login(LoginRequest request)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
		var user = userRepository.findByUsername(request.username()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return new TokenResponse(jwtToken, refreshToken, user.getUsername());
	}

	private void revokeAllUserTokens(User user)
	{
		final List<Token> validUserTokens = tokenRepository.findAllValidIsFalseOrRevokedIsFalseByUserId(user.getId());
		if (!validUserTokens.isEmpty())
		{
			for (final Token token : validUserTokens)
			{
				token.setExpired(true);
				token.setRevoked(true);
			}
			tokenRepository.saveAll(validUserTokens);
		}
	}

	public TokenResponse refresh(String authHeader)
	{
		if (authHeader == null || !authHeader.startsWith("Bearer "))
		{
			throw new IllegalArgumentException("Invalid Bearer Token");
		}

		final String refreshToken = authHeader.substring(7);
		final String username = jwtService.extractUsername(refreshToken);

		if (username == null)
		{
			throw new IllegalArgumentException("Invalid Refresh Token");
		}

		final User user = userRepository.findByUsername(username).orElseThrow();

		if (!jwtService.isTokenValid(refreshToken, user))
		{
			throw new IllegalArgumentException("Invalid Refresh Token");
		}

		final String accessToken = jwtService.generateToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, accessToken);
		return new TokenResponse(accessToken, refreshToken, user.getUsername());
	}
}
