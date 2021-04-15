package com.kgportal.business.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotNull @Size(min=3)
	private String haber; 
	private Date date;

}
