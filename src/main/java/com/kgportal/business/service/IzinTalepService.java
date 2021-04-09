package com.kgportal.business.service;

import java.util.List;
import com.kgportal.business.dto.IzinTalepDto;

public interface IzinTalepService {
	
	public Boolean save(IzinTalepDto izinTalepDto);
	
	public List<IzinTalepDto> getIzinTalepForUser();
	
	public List<IzinTalepDto> getIzinTalepForYonetici();
	
	public List<IzinTalepDto> getIzinTalepForAdmin();
	

}
