package com.example.demo.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter  {
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenManager jwtTtokenManager;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTtokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTtokenManager = jwtTtokenManager;
		
		this.setFilterProcessesUrl("/api/member/login");
	}
	
	// 로그인 시도
	// 요청에 인증 요구가 있을 때 실행됨
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.err.println("로그인 시도");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.err.println("username: " + username);
		System.err.println("password: " + password);
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
	
	// 로그인 성공시 실행하는 메서드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.err.println("로그인 성공");
		
		String accessToken = "";
		String refreshToken = "";
		try {
			accessToken = jwtTtokenManager.createAccessToken(authResult);
			refreshToken = jwtTtokenManager.createRefreshToken(authResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * 개발시 포트번호가 다르기 때문에 쿠키가 저장되지 않음
		 * Boot에 같이 빌드해서 배포하면 쿠키가 저장됨
		 * 즉, 같은 포트를 쓰면 저장됨
		 */
		
		response.setHeader("accessToken", accessToken);
	}
	
	// 로그인 실패시 실행하는 메서드
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.err.println("로그인 실패");
		
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().print(failed.getMessage());
	}
	
}
