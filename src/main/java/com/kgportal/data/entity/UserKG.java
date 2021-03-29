package com.kgportal.data.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.kgportal.utils.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKG {

	@Id
	private String username;
	private String password;
	private String name;
	private String surname;
	private Date birthDay;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
}
