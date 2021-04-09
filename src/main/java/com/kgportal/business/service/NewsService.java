package com.kgportal.business.service;

import java.util.List;

import com.kgportal.business.dto.NewsDto;
 

public interface NewsService {
	
	public Boolean save(NewsDto newsDto);
	
	public List<NewsDto> getAllNews();
}
