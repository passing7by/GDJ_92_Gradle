package com.example.demo.config.security;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

// 토큰 생성 및 검증

@Component
public class JwtTokenManager {
	// 1. access 토큰 유효 시간
	@Value("${jwt.accessValidTime}")
	private Long accessValidTime;
	
	// 2. refresh 토큰 유효 시간
	@Value("${jwt.refreshValidTime}")
	private Long refreshValidTime;
	
	// 3. 발급자
	@Value("${jwt.issuer}")
	private String issuer;
	
	// 4. Secret Key
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	// 5. Key
	private SecretKey key;
	
	@Autowired
	private MemberRepository memberRepository;
	
	// (1) 생성자 호출 후에 실행되는 메서드에서 secret key 암호화
	// 생성자 호출 전에 실행되는 메서드. 근데 꼭 생성자 호출 전에 해야되나? 생성자에서 하면 안 되나?
	// 생성자에서 해도 됨. 강사님은 @PostConstruct 를 소개시켜주려고 이 방법을 사용함~
	@PostConstruct // 생성자 호출 후에 실행하라는 뜻
	public void init() throws Exception {
		// secretKey를 암호화하여 key에 할당
		key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}
//	public JwtTokenManager() throws Exception {
//		// secretKey를 암호화하여 key에 할당
//		key = Keys.hmacShaKeyFor(secretKey.getBytes());
//	}
	
	// 토큰 생성 (클래스 내부 호출용)
	// access/refresh에 따라 다른 값을 받기 위해 validTime을 매개변수로 받음
	private String createToken(Authentication authentication, Long validTime) throws Exception { 
		String jwt = Jwts.builder()
						 .subject(authentication.getName()) // 사용자 id
						 .claim("roles", authentication.getAuthorities()) // 토큰에 넣고싶은 정보 (개발자가 임의로 추가한 정보)
						 .issuer(issuer) // 발급자
						 .signWith(key) // key
						 .issuedAt(new Date()) // 토큰 발급 시간
						 .expiration(new Date(System.currentTimeMillis() + validTime)) // 토큰 만료 시간
						 .compact(); // String 으로 변환
						 
		return jwt;
	}
	
	// access 토큰 생성
	public String createAccessToken(Authentication authentication) throws Exception {
		return this.createToken(authentication, accessValidTime);
	}
	
	// refresh 토큰 생성
	public String createRefreshToken(Authentication authentication) throws Exception {
		return this.createToken(authentication, refreshValidTime);
	}
	
	// 토큰 검증
	// 매개변수로 accessToken이 올 수도, refreshToken이 올 수도 있음
	// refresh 토큰을 어디에 저장할 것인지 고민할 필요 있음....cookie? db?
	public Authentication verifyToken(String token) throws Exception {
		// 검증에 실패하면 Exception 발생
		Claims claims = Jwts.parser()
							.verifyWith(key)
							.build()
							.parseSignedClaims(token)
							.getPayload();
		
		Optional<MemberVO> result = memberRepository.findById(claims.getSubject());
		MemberVO memberVO = result.get();
		
		// 맨 처음 매개변수에 userName만 넣어줘도 됨....진짜?
		Authentication authentication = new UsernamePasswordAuthenticationToken(memberVO, null, memberVO.getAuthorities());
		
		return authentication;
	}
	
	
}
