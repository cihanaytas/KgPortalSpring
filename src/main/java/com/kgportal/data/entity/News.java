package com.kgportal.data.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Lob
	private String haber; 
	
	private Date date;
}
