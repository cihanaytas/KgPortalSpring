package com.kgportal.data.entity;
 
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IzinTalep {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String izinTip;
	
	private Date talepTarih;
	
	@Temporal(TemporalType.DATE)
	private Date izinBaslangicTarih;
	
	@Temporal(TemporalType.DATE)
	private Date izinBitisTarih;
	
	private String aciklama;
	
	private String onayDurum;
	
	@ManyToOne
	@JoinColumn(name= "username")
	private UserKG user;
	
	@ManyToOne(cascade = CascadeType.MERGE)  //fetchtype hata veriyor
	@JoinColumn(name = "YoneticiUsername")
	private UserKG userYonetici;
	
	
}
