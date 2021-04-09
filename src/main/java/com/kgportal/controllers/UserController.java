package com.kgportal.controllers;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.kgportal.business.dto.IzinTalepDto;
import com.kgportal.business.service.IzinTalepService;
import com.kgportal.data.entity.UserKG;
import com.kgportal.data.entity.UserOrganization;
import com.kgportal.data.repository.UserBildirimRepository;
import com.kgportal.data.repository.UserOrganizationRepository;
import com.kgportal.data.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired 
	private UserRepository urep;
	
	@Autowired
	private UserOrganizationRepository uorep;

	
	@Autowired
	private UserBildirimRepository ubrep;
	
	@Autowired
	private IzinTalepService itService;

	@GetMapping("organization")
	public UserOrganization getOrganization() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserKG> user = urep.findById(authentication.getName());
		return uorep.getOrg(user.get());
		
	}
	
	
	@PostMapping("user/izintalep")
	public Boolean posttalep(@RequestBody IzinTalepDto talep) {

		if(itService.save(talep))
			return true;
		else
			return false;
		
	}
	
	
	@GetMapping("user/izintalepleri")
	public List<IzinTalepDto> getIzinTalep(){
		return itService.getIzinTalepForUser();
	}
	
	
	
	@GetMapping("user/bildirimler")
	public List<Object> getBildirim(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserKG> user = urep.findById(authentication.getName());
		List<Object> o = ubrep.getBildirim(user.get());

		return 	o;		 
	}
	
}
