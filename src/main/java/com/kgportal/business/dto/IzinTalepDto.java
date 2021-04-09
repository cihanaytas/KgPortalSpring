package com.kgportal.business.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IzinTalepDto {
	
	public IzinTalepDto(String izinTip, Date talepTarih, Date izinBitisTarih, String aciklama,
			String onayDurum) {
		this.izinTip = izinTip;
		this.talepTarih = talepTarih;
		this.izinBitisTarih = izinBitisTarih;
		this.aciklama = aciklama;
		this.onayDurum = onayDurum;
	}

	private long id;
	
	private String izinTip;
	
	private Date talepTarih;
	
	private Date izinBaslangicTarih;
	
	private Date izinBitisTarih;
	
	private String aciklama;
	
	private String onayDurum;
	
	private String username;
	
	private String user_name;
	
	private String user_surname;
	
	private String yonetici_username;
	
	private String yonetici_name;
	
	private String yonetici_surname;
}
