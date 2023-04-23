package com.bridgelabz.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class LoginDto {
    
	@NotEmpty
	@Size(min = 6,message = "please Enter MiniMum 6 character")
	private String username;
	@Size(min=6,message = "please Enter min 6 digit password")
	private String password;
	
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDto [username=" + username + ", password=" + password + "]";
	}

}
