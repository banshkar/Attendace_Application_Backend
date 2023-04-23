package com.bridgelabz.service;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.RegisterDto;
import com.bridgelabz.model.AttendanceModel;
import com.bridgelabz.model.FileModel;
import com.bridgelabz.model.User;

@Component
public interface UserServiceWIthAop {
	public ResponseEntity<Object> test();
	public ResponseEntity<Object> loginUser(LoginDto loginDto);
	public  ResponseEntity<Object> rigster(RegisterDto registerDto);
	public ResponseEntity<Object> getDetails(String token);
	public ResponseEntity<Object> update(String token,RegisterDto registerDto);
	public  ResponseEntity<List<User>> getAllUser(String token);
	public ResponseEntity<Object> LogOut(String token);
	public ResponseEntity<List<AttendanceModel>> getAttendanceReport(String token);
	public ResponseEntity<Object> signIn(String token);
	public ResponseEntity<Object> signOut(String token);
	public ResponseEntity<Object> deletedUser(String token,int id);
	public FileModel upLoadImage(MultipartFile multipartFile);
	public ResponseEntity<Object> download(int id);
}
