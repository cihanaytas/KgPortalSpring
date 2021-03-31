package com.kgportal.security;

import com.kgportal.data.entity.UserKG;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private String accessToken;
	private UserKG userkg;

}
