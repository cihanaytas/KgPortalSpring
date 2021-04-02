package com.kgportal.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgportal.data.entity.News;
import com.kgportal.data.entity.Talep;
import com.kgportal.data.repository.NewsRepository;
import com.kgportal.data.repository.TalepRepository;

@RestController
public class AdminController {

	@Autowired
	private NewsRepository nrep;
	
	@Autowired
	private TalepRepository trep;
	
	@PostMapping("admin/haber")
	public Boolean habergir(@RequestBody News haber) {
		Date date = new Date();
		haber.setDate(date);
		if(nrep.save(haber) != null)
			return true;
		else
			return false;
		
	}
	
	
	
	@GetMapping("admin/talepler")
	public List<Talep> getTalep(){
		List<Talep> list = (List<Talep>) trep.getAllTalep();
		return list;
	}
	
	//@Transactional
	@PostMapping("admin/onaytalep")
	public Talep taleponay(@RequestBody Talep talep) {
		if(trep.save(talep) != null)
		return talep;
		else
			return talep;			
	}
	


}
