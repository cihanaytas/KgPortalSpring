package com.kgportal.data.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Talep implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date tarih;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private UserKG user;
	
	private String roltanim;
	
 	private String proje;
	
	@NotNull
	private String rol;
	
	@NotNull
	private int sayi;
	
	@NotNull
	private long butce;
	
	private String onay;
	
	
	
}
