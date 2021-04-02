package com.kgportal.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kgportal.data.entity.Talep;

@Repository
public interface TalepRepository extends CrudRepository<Talep, Long>{
	
	@Query("select t from Talep t order by tarih desc")
	public List<Talep> getAllTalep();

}
