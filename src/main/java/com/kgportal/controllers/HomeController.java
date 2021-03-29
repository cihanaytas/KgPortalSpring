package com.kgportal.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgportal.business.dto.UserKGDto;
import com.kgportal.business.service.UserService;
import com.kgportal.data.repository.UserRepository;

@RestController
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home/birth")
	public List<UserKGDto> birthdays(){
		return userService.getBirthDays();
		

		
	}
}
