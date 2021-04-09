package com.kgportal.business.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
	
	public NewsDto(String haber) {
		this.haber = haber;
	}
	private long id;
	private String haber; 
	private Date date;

}
