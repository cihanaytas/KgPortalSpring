package com.kgportal.business.service;

import com.kgportal.data.entity.IzinTalep;
import com.kgportal.data.entity.UserBildirim;
import com.kgportal.data.entity.UserKG;

public interface UserBildirimService {

	public void save(UserKG user,String bildirim);
}
