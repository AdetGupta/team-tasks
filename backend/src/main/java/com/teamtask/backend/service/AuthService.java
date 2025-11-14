package com.teamtask.backend.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.teamtask.backend.dto.LoginUserDto;
import com.teamtask.backend.dto.RegisterUserDto;
import com.teamtask.backend.entity.User;
import com.teamtask.backend.exception.EmailAlreadyExistsException;
import com.teamtask.backend.exception.InvalidCredentialsException;
import com.teamtask.backend.exception.UserNotFoundException;
import com.teamtask.backend.repository.UserRepository;

@Service
public class AuthService {
	private UserRepository userRepository;
	private JwtEncoder jwtEncoder;
	private PasswordEncoder passwordEncoder;
	
	public AuthService(UserRepository userRepository, JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.jwtEncoder = jwtEncoder;
		this.passwordEncoder = passwordEncoder;
	}
	
	public String createUser(RegisterUserDto request) {
		User newUser = new User(request);
		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
		try{
			userRepository.save(newUser);
			return createToken(newUser);
		}catch(Exception e) {
			throw new EmailAlreadyExistsException();
		}
		
	}

	public String authenticateUser(LoginUserDto request) {
		User user = userRepository.findByEmail(request.getEmail()).orElseThrow(InvalidCredentialsException::new);
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new InvalidCredentialsException();
		}
		return createToken(user);
	}
	
	public User getUserFromToken(JwtAuthenticationToken auth) {
		Jwt jwt = auth.getToken();
		int userId = Integer.valueOf(jwt.getSubject());
		User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		return user;
	}
	
	private String createToken(User user) {
		var claims = JwtClaimsSet.builder()
				.issuer("slef")
				.issuedAt(Instant.now())
				.expiresAt(Instant.now().plusSeconds(60 * 2))
				.subject(String.valueOf(user.getUserId()))
				.claim("email", user.getEmail())
				.claim("name", user.getName())
				.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	
}
