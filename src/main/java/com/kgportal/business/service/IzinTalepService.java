package com.kgportal.business.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import com.kgportal.business.dto.IzinTalepDto;
import com.kgportal.data.entity.IzinTalep;

public interface IzinTalepService {
	
	public Boolean save(IzinTalepDto izinTalepDto);
	
	public Boolean YoneticiUpdateOnay(IzinTalep izinTalep, long talepId);
	
	public Boolean AdminUpdateOnay(IzinTalep izinTalep, long talepId);	
	
	public List<IzinTalepDto> getIzinTalepForUser();
	
	public List<IzinTalepDto> getIzinTalepForYonetici();
	
	public List<IzinTalepDto> getIzinTalepForAdmin();
	

}
