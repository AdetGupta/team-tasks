package com.teamtask.backend.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamtask.backend.dto.LoginUserDto;
import com.teamtask.backend.dto.RegisterUserDto;
import com.teamtask.backend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthService authService;	
	
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@GetMapping("/welcome")
	public ResponseEntity<Map<String, String>> welcome() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Map.of("message", "Welcome! This endpoint is not secure."));
	}
	
	@PostMapping("register")
	public ResponseEntity<Map<String, String>> create(@RequestBody RegisterUserDto request){
		String token = authService.createUser(request);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(Map.of("token", token));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, String>> authenticate(@RequestBody LoginUserDto request){
		String token= authService.authenticateUser(request);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Map.of("token", token));
	}
	
}
