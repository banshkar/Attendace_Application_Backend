package com.bridgelabz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String email;
	private String password;
	private String phoneNumber;
	private String attendanceMark="absent";
	private String sigInTime="Null";
	private String signOutTime="Null";
	private String date="Null";
	private boolean isLogin=false;
	@OneToOne(cascade = CascadeType.ALL)
	private DataStore dataStore;
	private String roll="user";
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getSigInTime() {
		return sigInTime;
	}
	public void setSigInTime(String sigInTime) {
		this.sigInTime = sigInTime;
	}
	public String getSignOutTime() {
		return signOutTime;
	}
	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getAttendanceMark() {
		return attendanceMark;
	}
	public void setAttendanceMark(String attendanceMark) {
		this.attendanceMark = attendanceMark;
	}
	public DataStore getDataStore() {
		return dataStore;
	}
	public void setDataStore(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", attendanceMark=" + attendanceMark + ", sigInTime=" + sigInTime
				+ ", signOutTime=" + signOutTime + ", date=" + date + ", isLogin=" + isLogin + ", dataStore="
				+ dataStore + ", roll=" + roll + "]";
	}
	
}
