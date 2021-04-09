package com.kgportal.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgportal.data.entity.UserKG;
import com.kgportal.data.entity.UserOrganization;


@Repository
public interface UserOrganizationRepository extends CrudRepository<UserOrganization, Long>{
	
	@Query("select u from UserOrganization u where u.user= :user")
	public UserOrganization getOrg(@Param(value="user") UserKG user);
	
	
	@Query("select u.userYonetici from UserOrganization u where u.user= :user")
	public UserKG getYonetici(@Param(value="user") UserKG user);

}
