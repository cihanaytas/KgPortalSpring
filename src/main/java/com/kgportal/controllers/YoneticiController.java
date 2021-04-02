package com.kgportal.controllers;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgportal.data.entity.Talep;
import com.kgportal.data.entity.UserKG;
import com.kgportal.data.repository.TalepRepository;
import com.kgportal.data.repository.UserRepository;

@RestController
public class YoneticiController {

	@Autowired
	private TalepRepository trep;
	
	@Autowired
	private UserRepository urep;

	@PostMapping("yonetici/talepekle")
	public Boolean talepekle(@Valid @RequestBody Talep talep) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Date date = new Date();
		talep.setTarih(date);
		talep.setOnay("ik");
		Optional<UserKG> user = urep.findById(authentication.getName());
		talep.setUser(user.get());
		if(trep.save(talep) != null)
			return true;
		else
			return false;
	}
	
	
}
