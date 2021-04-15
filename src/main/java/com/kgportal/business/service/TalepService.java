package com.kgportal.business.service;

import java.util.List;

import com.kgportal.business.dto.TalepDto;
import com.kgportal.data.entity.Talep;

public interface TalepService {
	
	public Boolean save(TalepDto talepDto);
	
	public Boolean AdminUpdateOnay(Talep talep, long talepId);
		
	public List<TalepDto> getYoneticiTalep();
	
	public List<TalepDto> getAdminTalep();

}
