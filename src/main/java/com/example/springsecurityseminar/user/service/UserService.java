package com.example.springsecurityseminar.user.service;

import org.springframework.stereotype.Service;

import com.example.springsecurityseminar.user.entity.User;
import com.example.springsecurityseminar.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public void create(User user) {
		userRepository.save(user);
	}

	public User read(Long id) {
		// return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
	}
}
