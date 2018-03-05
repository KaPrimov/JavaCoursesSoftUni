package com.softuni.carDealer.dtos.binding.add;

public class RegisterUser {
	
	private String username;
	private String email;
	private String password;
	
	public RegisterUser() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
