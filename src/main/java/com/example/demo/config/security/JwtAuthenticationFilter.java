package com.example.demo.config.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	
	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager manager, JwtTokenManager tokenManager) {
		super(manager);
		this.jwtTokenManager = tokenManager;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 이 형태로 헤더가 올 것임 -> Authorization: Bearer ${ACCESS_TOKEN}
		String header = request.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer")) {
			header = header.substring(header.indexOf(" ") + 1);
			
			// 토큰 검증
			try {
				Authentication authentication = jwtTokenManager.verifyToken(header);
				
				// 성공시 quthentication 객체를 session에 넣어줌
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				// TODO
				// access token이 만료되었지만, refreah token이 유효하다면
				// access token을 새로 발급하고, 로그인 시키고 doFilter로 통과
			}
		}
		
		chain.doFilter(request, response);
	}
	
}
