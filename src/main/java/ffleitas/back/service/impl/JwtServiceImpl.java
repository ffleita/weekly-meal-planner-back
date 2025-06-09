package ffleitas.back.service.impl;



import ffleitas.back.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtServiceImpl
{

	@Value("${application.security.jwt.secret-key}")
	private String secretKey;
	@Value("${application.security.jwt.expiration}")
	private long jwtExpiration;
	@Value("${application.security.jwt.refresh-token.expiration}")
	private long jwtRefreshExpiration;

	public String generateToken(final User user)
	{
		return buildToken(user, jwtExpiration);
	}

	public String generateRefreshToken(final User user)
	{
		return buildToken(user, jwtRefreshExpiration);
	}

	private String buildToken(User user, long expiration)
	{
		return Jwts.builder()
				.id(user.getId().toString())
				.claims(Map.of("username", user.getUsername(), "role", user.getRole()))
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignInKey())
				.compact();
	}

	public String extractUsername(String token)
	{
		return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	private SecretKey getSignInKey () {
		byte[] keyBytes = Base64.getDecoder().decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean isTokenValid(String token, User user)
	{
		final String username = extractUsername(token);
		return (username.equals(user.getUsername())) && !isTokenExpired(token);
	}

	public boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token)
	{
		return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload().getExpiration();
	}

	public String extractAuthority(String token)
	{
		Claims claims = Jwts
				.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claims.get("role", String.class);
	}
}
