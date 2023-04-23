package com.bridgelabz.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DataStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private List<AttendanceModel> attendanceModels;
	
	public DataStore() {
		super();
	}
	public DataStore(int id, List<AttendanceModel> attendanceModels) {
		super();
		this.id = id;
		this.attendanceModels = attendanceModels;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<AttendanceModel> getAttendanceModels() {
		return attendanceModels;
	}
	public void setAttendanceModels(List<AttendanceModel> attendanceModels) {
		this.attendanceModels = attendanceModels;
	}
	@Override
	public String toString() {
		return "DataStore [id=" + id + ", attendanceModels=" + attendanceModels + "]";
	}
	
	

}
