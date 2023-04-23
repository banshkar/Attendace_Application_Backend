package com.bridgelabz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.AttendanceModel;

public interface AttendanceRepo extends JpaRepository<AttendanceModel, Integer>{

}
