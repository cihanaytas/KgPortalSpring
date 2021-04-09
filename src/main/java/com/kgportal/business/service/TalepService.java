package com.kgportal.business.service;

import java.util.List;

import com.kgportal.business.dto.TalepDto;

public interface TalepService {
	
	public Boolean save(TalepDto talepDto);
	
	public List<TalepDto> getYoneticiTalep();
	
	public List<TalepDto> getAdminTalep();

}
