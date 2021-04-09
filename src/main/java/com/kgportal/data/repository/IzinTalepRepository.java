package com.kgportal.data.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.kgportal.data.entity.IzinTalep;
import com.kgportal.data.entity.UserKG;

@Repository
public interface IzinTalepRepository extends CrudRepository<IzinTalep, Long>{
	
	
	@Query("select i from IzinTalep i  where i.userYonetici= :user order by talepTarih desc")
	public List<IzinTalep> getTalepForYonetici(@Param("user") UserKG user);
	
	@Query("select i from IzinTalep i  where i.user= :user order by talepTarih desc")
	public List<IzinTalep> getTalepForUser(@Param("user") UserKG user);
	
	@Query("select i from IzinTalep i order by talepTarih desc")
	public List<IzinTalep> getTalepForAdmin();
}
