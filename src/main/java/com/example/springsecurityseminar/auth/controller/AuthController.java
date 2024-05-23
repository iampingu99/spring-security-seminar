package com.example.springsecurityseminar.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurityseminar.auth.dto.UserCreateDto;
import com.example.springsecurityseminar.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final UserService userService;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signup(@RequestBody UserCreateDto dto) {
		return ResponseEntity.created(null).build();
	}
}
