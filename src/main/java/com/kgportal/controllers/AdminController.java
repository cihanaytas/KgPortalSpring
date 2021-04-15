package com.kgportal.controllers;


import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.kgportal.business.dto.IzinTalepDto;
import com.kgportal.business.dto.NewsDto;
import com.kgportal.business.dto.TalepDto;
import com.kgportal.business.service.IzinTalepService;
import com.kgportal.business.service.NewsService;
import com.kgportal.business.service.TalepService;
import com.kgportal.business.service.UserBildirimService;
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
	private IzinTalepService itService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private UserBildirimService ubService;
	
	 Logger log = Logger.getLogger(AdminController.class);
	

	@PostMapping("admin/haber")
	public Boolean habergir(@Valid @RequestBody NewsDto haber) {

		if(newsService.save(haber))
			{
			return true;
			}
		else
		{
			log.error("Validation hatası");
			return false;}
		
	}
	
	
	
	@GetMapping("admin/talepler")
	public List<TalepDto> getTalep(){
		return talepService.getAdminTalep();
	}
	
	//@Transactional
	@PostMapping("admin/onaytalep/{talepId}/{durum}")
	public void taleponay(@PathVariable("talepId") Long talepId,@PathVariable("durum") String durum) {
		Optional<Talep> t = trep.findById(talepId);
		Talep talep = t.get();
		String bildirim = "";
		if(durum.equals("Onaylandı")) {
			bildirim = talepId + " no'lu talebiniz İK tarafından onaylanmıştır.";
			talep.setOnay(durum);
		}
		else if(durum.equals("Reddedildi")) {
			bildirim = talepId + " no'lu talebiniz İK tarafından reddedilmiştir.";
			talep.setOnay(durum);
		}
		
		talepService.AdminUpdateOnay(talep, talepId);
		ubService.save(talep.getUser(), bildirim);
		
	}
	
	
	@GetMapping("admin/izintalepleri")
	public List<IzinTalepDto> getIzinTalep(){
		return itService.getIzinTalepForAdmin();
	}
	
	
	@PostMapping("admin/izinonaytalep/{talepId}/{durum}")
	public void izintaleponay(@PathVariable("talepId") Long talepId,@PathVariable("durum") String durum) {
		Optional<IzinTalep> i = itrep.findById(talepId);
		IzinTalep izinTalep = i.get();
		String bildirim = "";
		if(durum.equals("Onaylandı")) {
			bildirim = talepId + " no'lu talebiniz İK tarafından onaylanmıştır.";
			izinTalep.setOnayDurum(durum);
		}
		else if(durum.equals("Reddedildi")) {
			bildirim = talepId + " no'lu talebiniz İK tarafından reddedilmiştir.";
			izinTalep.setOnayDurum(durum);
		}	
		
		itService.AdminUpdateOnay(izinTalep,talepId);
		ubService.save(izinTalep.getUser(),bildirim);
	}
	

	



}
