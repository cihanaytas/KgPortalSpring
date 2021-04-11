package com.kgportal.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kgportal.data.entity.UserKG;

@Repository
public interface UserRepository extends CrudRepository<UserKG, String>{
	
	@Query("SELECT u FROM UserKG as u WHERE DATE_FORMAT(u.birthDay, '%m-%d') = DATE_FORMAT( now(), '%m-%d')")
	public List<UserKG> birthdays();
 
}

