package com.kgportal.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgportal.data.entity.Talep;
import com.kgportal.data.entity.UserKG;

@Repository
public interface TalepRepository extends CrudRepository<Talep, Long>{
	
	@Query("select t from Talep t order by tarih desc")
	public List<Talep> getAllTalep();
	
	
	@Query("select t from Talep t  where t.user= :user order by tarih desc")
	public List<Talep> getMyTalep(@Param("user") UserKG user);
	
}
