package com.kgportal.security;

public class JwtResponse {


	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public JwtResponse() {
		super();
	}
	public JwtResponse(String accessToken, String id, String username, String email, String roles) {
		super();
		this.accessToken = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	private String accessToken;
	private String id;
	private String username;
	private String email;
	private String roles;

}
