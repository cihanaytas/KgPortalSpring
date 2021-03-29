package com.kgportal.business.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kgportal.business.dto.LoginBody;
import com.kgportal.business.dto.UserKGDto;

public interface UserService {

	public void addUser(UserKGDto userDto);
	
	public ResponseEntity<?> loginUser(LoginBody user);
	
	public List<UserKGDto> getBirthDays();
}
