package com.security.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.example.dao.UserRepository;
import com.security.example.dto.JoinDTO;
import com.security.example.entity.UserEntity;

@Service
public class JoinService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void joinProcess(JoinDTO joinDTO) {
		UserEntity user = new UserEntity();
		user.setUsername(joinDTO.getUsername());
		user.setPassword(encoder.encode(joinDTO.getPassword()));
		user.setRole("ROLE_USER");
		
		userRepository.save(user);
	}
}
