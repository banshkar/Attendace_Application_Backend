package com.bridgelabz.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class RegisterDto {
	
	
	@NotEmpty
	@Size(min = 6,message = "please Enter MiniMum 6 character")
	private String username;
	@Email(message = "Please enter valid Email")
	private String email;
	@Pattern(regexp = "^[A-Z]{1}+[A-za-z0-9]{5,15}+$",message = "char,numer")
	private String password;
	@Size(min=10,max=10,message = "enter 10 digit number")
	private String phoneNumber;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "RegisterDto [username=" + username + ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + "]";
	}
	

}
