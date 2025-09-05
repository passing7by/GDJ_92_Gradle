package com.example.demo.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private AddLogoutSuccessHandler addLogoutSuccessHandler;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// 0. 기본 설정
		httpSecurity
//			.cors(cors -> cors.disable()) // 개발 편의를 위해 disabled() 해놓음
			.cors(cors -> cors.configurationSource(this.corsConfiguration())) // 개발 편의를 위해 disabled() 해놓음
			.csrf(csrf -> csrf.disable()) // SSR에서는 csrf를 해주는게 맞고, CSR에서는 이것을 못 받아와야해서 disabled()해주어야 함
			
		// 1. 권한 설정
			.authorizeHttpRequests(
				auth -> auth
							.requestMatchers("/api/notice/add").hasRole("ADMIN")
							.requestMatchers("/api/notice/list").authenticated()
							.anyRequest().permitAll()
			)
		
		// 2. Form Login
			.formLogin(form -> form.disable())
			
		// 3. Logout 설정
			.logout(logout -> logout
									.logoutUrl("/api/member/logout")
									.invalidateHttpSession(true)
									.deleteCookies("access_token", "refresh_token")
//									.logoutUrl("/")
									.logoutSuccessHandler(addLogoutSuccessHandler)
			)
			
		// 4. Session 관련 설정
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
		// 5. HttpBasic
			.httpBasic(http -> http.disable())
		
		// 6. Token에 관련된 필터를 등록
			.addFilter(new JwtLoginFilter(this.authenticationConfiguration.getAuthenticationManager() , jwtTokenManager))
			.addFilter(new JwtAuthenticationFilter(this.authenticationConfiguration.getAuthenticationManager() , jwtTokenManager))
			;
		return httpSecurity.build();
	}
	
	// cors 설정
	// 다른 클래스에 해놓고 Bean으로 설정해도 됨
	CorsConfigurationSource corsConfiguration() { // CorsConfigurationSource는 어떤 인터페이스 쓰는게 맞는건지?
		CorsConfiguration configuration = new CorsConfiguration();
		// setAllowedOriginPatterns()를 사용하고 싶다면, setAllowCredentials()를 함께 사용해야 함
//		configuration.setAllowedOriginPatterns(List.of("http://localhost:5173")); // *도 사용 가능
//		configuration.setAllowCredentials(true);
		
		configuration.setAllowedOrigins(List.of("http://localhost:5173")); // *도 사용 가능
		
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS")); // *은 사용 불가
		configuration.setAllowedHeaders(List.of("Authorization")); // 헤더를 허락해줘야 함 | *도 사용 가능
		
		configuration.setExposedHeaders(List.of("accessToken")); // 이걸 추가해야 헤더를 클라이언트로 내보낼 수 있음
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
}
