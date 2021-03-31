package com.kgportal.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgportal.business.dto.UserKGDto;
import com.kgportal.business.service.UserService;
import com.kgportal.data.entity.News;
import com.kgportal.data.repository.NewsRepository;
import com.kgportal.data.repository.UserRepository;

@RestController
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NewsRepository nr;
	
	@GetMapping("/home/birth")
	public List<UserKGDto> birthdays(){
		return userService.getBirthDays();
		
	}
	
	@GetMapping("home/news")
	public List<News> gern(){
		List<News> list = (List<News>) nr.getAllNews();
		return list;
	}
	
	
	@GetMapping("admin")
	public String adfghd() {
		return "Admin Page";
	}
}
