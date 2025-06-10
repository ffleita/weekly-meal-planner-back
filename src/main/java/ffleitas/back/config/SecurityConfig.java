package ffleitas.back.config;

import ffleitas.back.domain.entities.Token;
import ffleitas.back.domain.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig
{
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	private final JwtAuthFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final TokenRepository tokenRepository;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(req ->
						req.requestMatchers("/auth/**",
										"/swagger-ui/**",
										"/v3/api-docs/**",
										"/swagger-ui.html",
										"/swagger-resources/**",
										"/webjars/**").permitAll()
								.anyRequest().authenticated()
						)
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(logout -> logout.logoutUrl("/auth/logout").addLogoutHandler(((request, response, authentication) -> {
					final var authHeader = request.getHeader("Authorization");
					logout(authHeader);
				})).logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext())))
		;

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:5173")); // frontend local
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*")); // o especificar los necesarios
		config.setAllowCredentials(true);       // importante si usás cookies o sesiones

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	private void logout(String token)
	{
		if (token == null || !token.startsWith("Bearer ")) {
			throw new IllegalArgumentException("Invalid token");
		}
		final String jwtToken = token.substring(7);
		final Token foundToken = tokenRepository.findTokenWithJwttoken(jwtToken);
		foundToken.setRevoked(true);
		foundToken.setExpired(true);
		tokenRepository.save(foundToken);
		log.info("Logout exitoso, token: {}", jwtToken);
	}
}
