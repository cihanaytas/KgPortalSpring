package com.kgportal.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgportal.data.entity.UserBildirim;
import com.kgportal.data.entity.UserKG;
import com.kgportal.utils.CUserBildirim;

@Repository
public interface UserBildirimRepository extends CrudRepository<UserBildirim, Long>{
	
	//@Query("select count()b.bildirim,b.date from UserBildirim b where b.user= :user")
	@Query("SELECT count(case when okundu=false then 1 end),b.bildirim,b.date FROM UserBildirim b where b.user= :user")
	public List<Object> getBildirim(@Param(value="user") UserKG user);

}
