package com.kgportal.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kgportal.business.dto.NewsDto;
import com.kgportal.business.service.NewsService;
import com.kgportal.data.entity.News;
import com.kgportal.data.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService{
	
    Logger log = Logger.getLogger(NewsServiceImpl.class);
	
	@Autowired
	private NewsRepository nrep;

	@Override
	public Boolean save(NewsDto newsDto) {
		News news = new News();
		Date date = new Date();
		news.setDate(date);
		convertToEntity(news,newsDto);
		if(nrep.save(news) != null) {
			log.info("Haber ekleme başarılı");
			return true;
			}
		else {
			log.error("Haber ekleme işlemi başarısız");
			return false;}
	}
	
	@Override
	public List<NewsDto> getAllNews() {
		List<NewsDto> listDto = new ArrayList<>();
		try {
			List<News> list = nrep.getAllNews();
			for(News news : list) {
				NewsDto ndto = new NewsDto();
				convertToDto(news, ndto);
				listDto.add(ndto);
			}
			log.info("Haberler listelendi.");

		}
		catch (Exception e) {
			log.error("Haberler listelenemedi.");
			// TODO: handle exception
		}
		
		
		return listDto;

	}
	
	
	private void convertToEntity(News news, NewsDto newsDto) {
		news.setHaber(newsDto.getHaber());
	}
	
	private void convertToDto(News news, NewsDto newsDto) {
		newsDto.setId(news.getId());
		newsDto.setDate(news.getDate());
		newsDto.setHaber(news.getHaber());
	}



	


}
