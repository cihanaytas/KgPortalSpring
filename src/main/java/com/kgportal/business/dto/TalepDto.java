package com.kgportal.business.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalepDto {
	
	public TalepDto(String roltanim, String proje, String rol, int sayi, long butce) {
		this.roltanim = roltanim;
		this.proje = proje;
		this.rol = rol;
		this.sayi = sayi;
		this.butce = butce;
	}
	private long id;
	private Date tarih;
	private String roltanim;
	private String proje;
	private String rol;
	private int sayi;
	private long butce;
	private String onay;
	private String username;
	private String name;
	private String surname;
	
}
