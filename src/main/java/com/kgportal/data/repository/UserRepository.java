package com.kgportal.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kgportal.data.entity.UserKG;

@Repository
public interface UserRepository extends CrudRepository<UserKG, String>{
 
}
