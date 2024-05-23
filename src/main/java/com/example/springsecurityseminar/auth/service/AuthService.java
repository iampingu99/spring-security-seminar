package com.example.springsecurityseminar.auth.service;

import org.springframework.stereotype.Service;

import com.example.springsecurityseminar.auth.dto.SignInDto;
import com.example.springsecurityseminar.auth.entity.User;
import com.example.springsecurityseminar.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final JwtUtil jwtUtil;

	public String signIn(SignInDto dto) {
		User user = userService.read(dto.getUsername());
		if (user.getPassword().equals(dto.getPassword())) {
			return jwtUtil.generateToken(user.getUsername(), user.getId());
		} else {
			throw new IllegalArgumentException("Invalid password");
		}
	}
}
