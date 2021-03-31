package com.kgportal.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgportal.data.entity.News;
import com.kgportal.data.repository.NewsRepository;

@RestController
public class AdminController {

	@Autowired
	private NewsRepository nrep;
	
	@PostMapping("admin/haber")
	public Boolean habergir(@RequestBody News haber) {
		Date date = new Date();
		haber.setDate(date);
		if(nrep.save(haber) != null)
			return true;
		else
			return false;
		
	}
}
