package com.kgportal.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
 public class UserOrganization implements Serializable{



	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "username")
	private UserKG user;
	
	@ManyToOne(cascade = CascadeType.MERGE)  //fetchtype hata veriyor
	@JoinColumn(name = "YoneticiUsername")
	private UserKG userYonetici;
	
	private Date iseGirisTarih;
	
	private String proje;
	
	private String unvan;
	
	
	
	
	
	
}
