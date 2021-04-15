package com.kgportal.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.kgportal.business.dto.IzinTalepDto;
import com.kgportal.business.service.IzinTalepService;
import com.kgportal.data.entity.IzinTalep;
import com.kgportal.data.entity.UserKG;
import com.kgportal.data.repository.IzinTalepRepository;
import com.kgportal.data.repository.UserOrganizationRepository;
import com.kgportal.data.repository.UserRepository;
import com.kgportal.utils.SendMail;

@Service
public class IzinTalepServiceImpl implements IzinTalepService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserOrganizationRepository uorep;
	
	@Autowired
	private IzinTalepRepository itrep;
	
	@Autowired
	private SendMail mailSender;

	@Override
	public Boolean save(IzinTalepDto izinTalepDto) {
		IzinTalep izinTalep = new IzinTalep();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserKG> user = userRepository.findById(authentication.getName());
		UserKG userYonetici = uorep.getYonetici(user.get());
		Date date = new Date();
		izinTalep.setTalepTarih(date);
		izinTalep.setUser(user.get());
		izinTalep.setUserYonetici(userYonetici);
		izinTalepDto.setOnayDurum("yonetici");
		mailSender.sendMail(userYonetici.getUsername(), user.get().getName() + " " +  user.get().getSurname() + " izin talebinde bulundu."
				, "Personel İzin Talebi");	
		converToEntity(izinTalep,izinTalepDto);			
		
		if(itrep.save(izinTalep) != null)
			return true;
		else
			return false;
	}
	
	
	@Override
	public Boolean YoneticiUpdateOnay(IzinTalep izinTalep, long talepId) {

		if(izinTalep.getOnayDurum().equals("ik")) {
			mailSender.sendMail(izinTalep.getUser().getUsername(), talepId + " no'lu izin talebiniz yöneticiniz " + izinTalep.getUserYonetici().getName() 
					+" " + izinTalep.getUserYonetici().getSurname() +  " tarafından onaylanıp İK'ya iletilmiştir."
					, "İzin Talebi Onay Bilgilendirme");			
		}
		
		else if(izinTalep.getOnayDurum().equals("Reddedildi")) {
			mailSender.sendMail(izinTalep.getUser().getUsername(), talepId + " no'lu izin talebiniz yöneticiniz " + izinTalep.getUserYonetici().getName() 
					+" " + izinTalep.getUserYonetici().getSurname() +  " tarafından reddedilmiştir."
					, "İzin Talebi Onay Bilgilendirme");			
		}

		if(itrep.save(izinTalep) != null)
			return true;
		else
			return false;
	}
	

	
	@Override
	public Boolean AdminUpdateOnay(IzinTalep izinTalep, long talepId) {

		if(izinTalep.getOnayDurum().equals("Onaylandı")){	
			mailSender.sendMail(izinTalep.getUser().getUsername(), talepId + " no'lu izin talebiniz İK tarafından onaylanmıştır."
					, "İzin Talebi Onay Bilgilendirme");
			
		}
		
		else if(izinTalep.getOnayDurum().equals("Reddedildi")) {
			mailSender.sendMail(izinTalep.getUser().getUsername(), talepId + " no'lu izin talebiniz İK tarafından reddedilmiştir."
					, "İzin Talebi Onay Bilgilendirme");			
		}	
		
		if(itrep.save(izinTalep) != null)
			return true;
		else
			return false;
	}


	
	@Override
	public List<IzinTalepDto> getIzinTalepForUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserKG> user = userRepository.findById(authentication.getName());
		List<IzinTalep> list = itrep.getTalepForUser(user.get());
		List<IzinTalepDto> listDto = new ArrayList<>();
		for(IzinTalep izinTalep : list) {
			IzinTalepDto izinTalepDto = new IzinTalepDto();
			converToDto(izinTalep, izinTalepDto);
			listDto.add(izinTalepDto);
		}
		return listDto;
	}
	
	
	@Override
	public List<IzinTalepDto> getIzinTalepForYonetici() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<UserKG> user = userRepository.findById(authentication.getName());		
		List<IzinTalep> list = itrep.getTalepForYonetici(user.get());
		List<IzinTalepDto> listDto = new ArrayList<>();
		for(IzinTalep izinTalep : list) {
			IzinTalepDto izinTalepDto = new IzinTalepDto();
			converToDto(izinTalep, izinTalepDto);
			listDto.add(izinTalepDto);
		}
		return listDto;
	}


	@Override
	public List<IzinTalepDto> getIzinTalepForAdmin() {
		List<IzinTalep> list = itrep.getTalepForAdmin();
		List<IzinTalepDto> listDto = new ArrayList<>();
		for(IzinTalep izinTalep : list) {
			IzinTalepDto izinTalepDto = new IzinTalepDto();
			converToDto(izinTalep, izinTalepDto);
			listDto.add(izinTalepDto);
		}
		return listDto;
	}


	
	
	
	
	private void converToEntity(IzinTalep izinTalep,IzinTalepDto izinTalepDto){
		izinTalep.setIzinTip(izinTalepDto.getIzinTip());
		izinTalep.setIzinBaslangicTarih(izinTalepDto.getIzinBaslangicTarih());
		izinTalep.setIzinBitisTarih(izinTalepDto.getIzinBitisTarih());
		izinTalep.setAciklama(izinTalepDto.getAciklama());
		izinTalep.setOnayDurum(izinTalepDto.getOnayDurum());

		
	}
	
	private void converToDto(IzinTalep izinTalep,IzinTalepDto izinTalepDto){
		izinTalepDto.setId(izinTalep.getId());
		izinTalepDto.setIzinTip(izinTalep.getIzinTip());
		izinTalepDto.setTalepTarih(izinTalep.getTalepTarih());
		izinTalepDto.setIzinBaslangicTarih(izinTalep.getIzinBaslangicTarih());
		izinTalepDto.setIzinBitisTarih(izinTalep.getIzinBitisTarih());
		izinTalepDto.setAciklama(izinTalep.getAciklama());
		izinTalepDto.setOnayDurum(izinTalep.getOnayDurum());
		izinTalepDto.setUsername(izinTalep.getUser().getUsername());
		izinTalepDto.setUser_name(izinTalep.getUser().getName());
		izinTalepDto.setUser_surname(izinTalep.getUser().getSurname());
		izinTalepDto.setYonetici_name(izinTalep.getUserYonetici().getName());
		izinTalepDto.setYonetici_surname(izinTalep.getUserYonetici().getSurname());
		izinTalepDto.setYonetici_username(izinTalep.getUserYonetici().getUsername());
	}



















}
