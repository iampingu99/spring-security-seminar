package com.example.springsecurityseminar.user.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurityseminar.user.entity.User;
import com.example.springsecurityseminar.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public User create(User user) {
		return userRepository.save(user);
	}

	public User read(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}
}
