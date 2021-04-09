package com.kgportal.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kgportal.business.dto.NewsDto;
import com.kgportal.business.dto.UserKGDto;
import com.kgportal.business.service.NewsService;
import com.kgportal.business.service.UserService;


@RestController
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/home/birth")
	public List<UserKGDto> birthdays(){
		return userService.getBirthDays();
		
	}
	
	@GetMapping("home/news")
	public List<NewsDto> getNews(){
		return newsService.getAllNews();
	}
	

}
