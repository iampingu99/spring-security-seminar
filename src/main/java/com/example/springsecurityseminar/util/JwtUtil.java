package com.example.springsecurityseminar.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.springsecurityseminar.auth.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {
	private final UserService userService;
	@Value("${jwt.secret}")
	private String secretKey;
	@Value("${jwt.expiration}")
	private long expiration;
	@Value("${jwt.issuer}")
	private String issuer;

	/**
	 * AccessToken 생성 메소드
	 * @param username
	 * @param userId
	 * @return
	 */
	public String generateToken(String username, Long userId) {
		return Jwts.builder()
				.setSubject(username)
				.claim("userId", userId)
				.setIssuer(issuer)
				.setIssuedAt(new java.util.Date(System.currentTimeMillis()))
				.setExpiration(new java.util.Date(System.currentTimeMillis() + expiration))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secretKey.getBytes())
				.compact();
	}

	/**
	 * JWT 유효성 검사 메소드
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		try {
			// Bearer 검증
			if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
				return false;
			} else {
				token = token.split(" ")[1].trim();
			}
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
			// 만료되었을 시 false
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * HTTP Header에서 JWT 토큰을 가져오는 메소드
	 * @param request
	 * @return
	 */
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

	/**
	 * JWT 토큰에서 userId 추출
	 * @param token
	 * @return
	 */
	public Long getUserId(String token) {
		Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
		return claims.getBody().get("userId", Long.class);
	}
}
