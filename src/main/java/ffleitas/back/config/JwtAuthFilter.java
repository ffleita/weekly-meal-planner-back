package ffleitas.back.config;

import ffleitas.back.domain.entities.Token;
import ffleitas.back.domain.repositories.TokenRepository;
import ffleitas.back.service.impl.JwtServiceImpl;
import ffleitas.back.domain.entities.User;
import ffleitas.back.domain.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter
{

	private final JwtServiceImpl jwtService;
	private final UserDetailsService userDetailsService;
	private final TokenRepository tokenRepository;
	private final UserRepository  userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		if (request.getServletPath().equals("/auth")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String jwtToken  = authHeader.substring(7);
		final String username = jwtService.extractUsername(jwtToken);
		if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
			return;
		}

		final Token token = tokenRepository.findTokenWithJwttoken(jwtToken);
		if (token.isExpired() || token.isRevoked()) {
			filterChain.doFilter(request, response);
			return;
		}

		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		final Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		}

		final boolean isTokenValid = jwtService.isTokenValid(jwtToken, user.get());
		if (!isTokenValid) {
			return;
		}

		String role = jwtService.extractAuthority(jwtToken);

		var authorities = new SimpleGrantedAuthority("ROLE_" + role);

		final var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, List.of(authorities));

		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);
	}
}
