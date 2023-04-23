package com.bridgelabz.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AttendanceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String date =String.valueOf(LocalDate.now());
	private String signIn;
	private String signOut;
	private String attendancemark;
	public AttendanceModel() {

	}
	
	public AttendanceModel(int id, String date, String signIn, String signOut, String attendancemark) {
		super();
		this.id = id;
		this.date = date;
		this.signIn = signIn;
		this.signOut = signOut;
		this.attendancemark = attendancemark;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSignIn() {
		return signIn;
	}
	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}
	public String getSignOut() {
		return signOut;
	}
	public void setSignOut(String signOut) {
		this.signOut = signOut;
	}

	public String getAttendancemark() {
		return attendancemark;
	}

	public void setAttendancemark(String attendancemark) {
		this.attendancemark = attendancemark;
	}

	@Override
	public String toString() {
		return "AttendanceModel [id=" + id + ", date=" + date + ", signIn=" + signIn + ", signOut=" + signOut
				+ ", attendancemark=" + attendancemark + "]";
	}
	

}
