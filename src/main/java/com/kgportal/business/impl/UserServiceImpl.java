package com.kgportal.business.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
		Optional<UserKG> ukg = userRepository.findById(user.getUsername());
		


		return ResponseEntity.ok(new JwtResponse(jwt,
												 ukg.get()));
	}

	
	
	
	@Override
	public List<UserKGDto> getBirthDays() {
		List<UserKG> list = new ArrayList<>();
		LocalDate myObj = LocalDate.now();
		list = userRepository.birthdays(); 
		List<UserKGDto> list2 = new ArrayList<>();
		
		for(UserKG user: list ) { 
			UserKGDto userDto = new UserKGDto();
			convertToDto(user, userDto);
			list2.add(userDto);
		}
		
		return list2;
	}
	
	
	
	
	
	
	
	public void convertToEntity(UserKG user, UserKGDto userDto) {		
		
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		user.setBirthDay(userDto.getBirthDay());
		user.setName(userDto.getName());
		user.setSurname(userDto.getSurname());
		

	}
	
	
	public void convertToDto(UserKG user, UserKGDto userDto) {
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setRole(user.getRole());
		userDto.setBirthDay(user.getBirthDay());
		userDto.setName(user.getName());
		userDto.setSurname(user.getSurname());
		
	}








}
