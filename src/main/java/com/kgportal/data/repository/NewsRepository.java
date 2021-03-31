package com.kgportal.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kgportal.data.entity.News;

@Repository
public interface NewsRepository extends CrudRepository<News, Long>{
	
	@Query("select n from News n order by date desc")
	public List<News> getAllNews();

}
