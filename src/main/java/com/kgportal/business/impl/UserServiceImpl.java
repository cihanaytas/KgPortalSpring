package com.kgportal.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kgportal.business.dto.LoginBody;
import com.kgportal.business.dto.UserKGDto;
import com.kgportal.business.service.UserService;
import com.kgportal.data.entity.UserKG;
import com.kgportal.data.repository.UserRepository;
import com.kgportal.security.JwtResponse;
import com.kgportal.security.JwtUtils;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;
    
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void addUser(UserKGDto userDto) {
		UserKG user = new UserKG();
		convertToEntity(user, userDto);
		userRepository.save(user);
		
	}
	
	@Override
	public ResponseEntity<?> loginUser(LoginBody user) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = "Bearer " + jwtUtils.generateJwtToken(authentication);
		



		return ResponseEntity.ok(new JwtResponse(jwt, 
												 "1", 
												 user.getUsername(), 
												 "aaaaaaaa", 
												 "ROLE_ADMIN"));
	}

	
	
	public void convertToEntity(UserKG user, UserKGDto userDto) {		
		
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		

	}
	
	
	public void convertToDto(UserKG user, UserKGDto userDto) {
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setRole(user.getRole());
	}






}
