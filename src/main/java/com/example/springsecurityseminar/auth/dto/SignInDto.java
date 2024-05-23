package com.example.springsecurityseminar.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInDto {
	private String username;
	private String password;
}
