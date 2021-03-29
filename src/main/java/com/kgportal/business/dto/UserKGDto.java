package com.kgportal.business.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.springframework.lang.Nullable;

import com.kgportal.utils.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKGDto {
	
	@Email
	private String username;
	
	@NotNull
	@Size(min=6, max= 18)
	private String password;
	
	@NotNull
	private UserRole role;

}
