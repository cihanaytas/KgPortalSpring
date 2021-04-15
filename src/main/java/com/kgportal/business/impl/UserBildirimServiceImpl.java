package com.kgportal.business.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgportal.business.service.UserBildirimService;
import com.kgportal.data.entity.IzinTalep;
import com.kgportal.data.entity.UserBildirim;
import com.kgportal.data.entity.UserKG;
import com.kgportal.data.repository.UserBildirimRepository;

@Service
public class UserBildirimServiceImpl implements UserBildirimService{

	
	@Autowired
	private UserBildirimRepository ubrep;
	
	@Override
	public void save(UserKG user,String bildirim) {
		UserBildirim userBildirim = new UserBildirim();
		Date date = new Date();
		userBildirim.setBildirim(bildirim);
		userBildirim.setUser(user);
		userBildirim.setDate(date);
		userBildirim.setOkundu(false);
		ubrep.save(userBildirim);
		
	}
	


}
