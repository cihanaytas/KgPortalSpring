package com.kgportal.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.kgportal.business.dto.IzinTalepDto;
import com.kgportal.business.dto.TalepDto;
import com.kgportal.business.service.IzinTalepService;
import com.kgportal.business.service.TalepService;
import com.kgportal.business.service.UserBildirimService;
import com.kgportal.data.entity.IzinTalep;
import com.kgportal.data.entity.Talep;
import com.kgportal.data.entity.UserBildirim;
import com.kgportal.data.entity.UserKG;
import com.kgportal.data.repository.IzinTalepRepository;
import com.kgportal.data.repository.TalepRepository;
import com.kgportal.data.repository.UserBildirimRepository;
import com.kgportal.data.repository.UserRepository;
import org.apache.log4j.Logger;

@RestController
public class YoneticiController {

	@Autowired
	private TalepRepository trep;
	
	@Autowired
	private UserRepository urep;
	
	@Autowired
	private IzinTalepRepository itrep;

	
	@Autowired
	private IzinTalepService itService;
	
	@Autowired
	private TalepService talepService;
	
	@Autowired
	private UserBildirimService ubService;
	

	@PostMapping("yonetici/talepekle")
	public Boolean talepekle(@Valid @RequestBody TalepDto talep) {
		if(talepService.save(talep) != null)
			return true;
		else
			return false;
	}
	
	
	@GetMapping("yonetici/taleplerim")
	public List<TalepDto> getTalep(){
		return talepService.getYoneticiTalep();
	}
	
	
	@GetMapping("yonetici/izintalepleri")
	public List<IzinTalepDto> getIzinTalep(){
		return itService.getIzinTalepForYonetici();
	}
	
	
	@PostMapping("yonetici/onaytalep/{talepId}/{durum}")
	public void taleponay(@PathVariable("talepId") Long talepId,@PathVariable("durum") String durum) {
		Optional<IzinTalep> i = itrep.findById(talepId);
		IzinTalep izinTalep = i.get();
		String bildirim = "";
		if(durum.equals("ik")) {
			bildirim = "Talebiniz yöneticiniz tarafından İK'ya iletilmiştir.";
			izinTalep.setOnayDurum(durum);
		}
		else if(durum.equals("Reddedildi")) {
			bildirim = "Talebiniz yöneticiniz tarafından reddedilmiştir.";
			izinTalep.setOnayDurum(durum);
		}
		
		itService.YoneticiUpdateOnay(izinTalep,talepId);
		ubService.save(izinTalep.getUser(),bildirim);
		
	}
	
	

	
}
