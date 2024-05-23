package com.example.springsecurityseminar.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurityseminar.user.entity.User;
import com.example.springsecurityseminar.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final UserService userService;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signup(@RequestBody User user) {
		return ResponseEntity.ok(userService.create(user));
	}
}
