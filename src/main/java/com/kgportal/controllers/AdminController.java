package com.kgportal.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.kgportal.business.dto.IzinTalepDto;
import com.kgportal.business.dto.NewsDto;
import com.kgportal.business.dto.TalepDto;
import com.kgportal.business.service.IzinTalepService;
import com.kgportal.business.service.NewsService;
import com.kgportal.business.service.TalepService;
import com.kgportal.data.entity.IzinTalep;
import com.kgportal.data.entity.Talep;
import com.kgportal.data.entity.UserBildirim;
import com.kgportal.data.repository.IzinTalepRepository;
import com.kgportal.data.repository.TalepRepository;
import com.kgportal.data.repository.UserBildirimRepository;
 

@RestController
public class AdminController {

	
	@Autowired
	private TalepRepository trep;
	
	@Autowired
	private TalepService talepService;
	
	@Autowired
	private IzinTalepRepository itrep;
	
	@Autowired
	private UserBildirimRepository ubrep;

	@Autowired
	private IzinTalepService itService;
	
	@Autowired
	private NewsService newsService;
	
	@PostMapping("admin/haber")
	public Boolean habergir(@RequestBody NewsDto haber) {
		if(newsService.save(haber))
			return true;
		else
			return false;
		
	}
	
	
	
	@GetMapping("admin/talepler")
	public List<TalepDto> getTalep(){
		return talepService.getAdminTalep();
	}
	
	//@Transactional
	@PostMapping("admin/onaytalep")
	public Talep taleponay(@RequestBody Talep talep) {
		if(trep.save(talep) != null)
		return talep;
		else
			return talep;			
	}
	
	
	@GetMapping("admin/izintalepleri")
	public List<IzinTalepDto> getIzinTalep(){
		return itService.getIzinTalepForAdmin();
	}
	
	
	@PostMapping("admin/izinonaytalep")
	public IzinTalep taleponay(@RequestBody IzinTalep talep) {
		if(itrep.save(talep) != null)
		return talep;
		else
			return talep;			
	}
	
	@PostMapping("admin/bildirimgonder")
	public Boolean bildirimgonder(@RequestBody UserBildirim userBildirim) {
		Date date = new Date();
		userBildirim.setDate(date);
		userBildirim.setOkundu(false);
		if(ubrep.save(userBildirim) != null)
			return true;
		else
			return false;
	}
	
	



}
