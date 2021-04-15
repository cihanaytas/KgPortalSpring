package com.kgportal.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kgportal.business.dto.TalepDto;
import com.kgportal.business.service.TalepService;
import com.kgportal.data.entity.Talep;
import com.kgportal.data.entity.UserKG;
import com.kgportal.data.repository.TalepRepository;
import com.kgportal.data.repository.UserRepository;
import com.kgportal.utils.SendMail;

@Service
public class TalepServiceImpl implements TalepService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TalepRepository talepRepository;
	
	@Autowired
	private SendMail mailSender;

	@Override
	public Boolean save(TalepDto talepDto) {
		Talep talep = new Talep();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Date date = new Date();
		talep.setTarih(date);
		talep.setOnay("ik");
		Optional<UserKG> user = userRepository.findById(authentication.getName());
		talep.setUser(user.get());
		convertToEntity(talep, talepDto);
		if(talepRepository.save(talep) != null)
			return true;
		else
			return false;
	}

	@Override
	public List<TalepDto> getYoneticiTalep() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserKG> user = userRepository.findById(authentication.getName());
		List<Talep> list = talepRepository.getMyTalep(user.get());;
		List<TalepDto> listDto = new ArrayList<>();
		for(Talep talep : list) {
			TalepDto talepDto = new TalepDto();
			converToDto(talep, talepDto);
			listDto.add(talepDto);
		}
		return listDto;
	}
	
	@Override
	public List<TalepDto> getAdminTalep() {
		List<Talep> list = talepRepository.getAllTalep();
		List<TalepDto> listDto = new ArrayList<>();
		for(Talep talep : list) {
			TalepDto talepDto = new TalepDto();
			converToDto(talep, talepDto);
			listDto.add(talepDto);
		}
		return listDto;
	}
	
	
	@Override
	public Boolean AdminUpdateOnay(Talep talep, long talepId) {
		if(talep.getOnay().equals("Onaylandı")){	
			mailSender.sendMail(talep.getUser().getUsername(), talepId + " no'lu izin talebiniz İK tarafından onaylanmıştır."
					, "İzin Talebi Onay Bilgilendirme");
			
		}
		
		else if(talep.getOnay().equals("Reddedildi")) {
			mailSender.sendMail(talep.getUser().getUsername(), talepId + " no'lu izin talebiniz İK tarafından reddedilmiştir."
					, "İzin Talebi Onay Bilgilendirme");			
		}	
		
		if(talepRepository.save(talep) != null)
			return true;
		else
			return false;
	}

	
	private void convertToEntity(Talep talep, TalepDto talepDto) {
		talep.setRol(talepDto.getRol());
		talep.setProje(talepDto.getProje());
		talep.setRoltanim(talepDto.getRoltanim());
		talep.setSayi(talepDto.getSayi());
		talep.setButce(talepDto.getButce());
	}
	
	private void converToDto(Talep talep, TalepDto talepDto) {
		talepDto.setId(talep.getId());
		talepDto.setTarih(talep.getTarih());
		talepDto.setRol(talep.getRol());
		talepDto.setProje(talep.getProje());
		talepDto.setRoltanim(talep.getRoltanim());
		talepDto.setSayi(talep.getSayi());
		talepDto.setButce(talep.getButce());
		talepDto.setOnay(talep.getOnay());
		talepDto.setUsername(talep.getUser().getUsername());
		talepDto.setName(talep.getUser().getName());
		talepDto.setSurname(talep.getUser().getSurname());
	}




}
