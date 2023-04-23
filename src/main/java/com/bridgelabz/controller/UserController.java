package com.bridgelabz.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.RegisterDto;
import com.bridgelabz.model.AttendanceModel;
import com.bridgelabz.model.FileModel;
import com.bridgelabz.model.User;

import com.bridgelabz.service.UserServicesWithSecurity;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserServicesWithSecurity userServicesImpl;
	
	
	
 Logger logger =LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/hello")
	public ResponseEntity<Object> hell() {
		return this.userServicesImpl.test();
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody RegisterDto registerDto){
		
		return this.userServicesImpl.rigster( registerDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto){
		return this.userServicesImpl.loginUser(loginDto);
	}
	@PostMapping("/logout")
	public ResponseEntity<Object> logOut(@Valid @RequestParam String token){
		return this.userServicesImpl.LogOut(token);
		
	}
	
	@GetMapping("/details")
    public ResponseEntity<Object> getDetails( @RequestParam String token){
	   return this.userServicesImpl.getDetails(token);
   }
	@GetMapping("/getAttendance")
	public ResponseEntity<List<AttendanceModel>> getAttendanceMark(@Valid @RequestParam String token){
		
		return this.userServicesImpl.getAttendanceReport(token);
	}
	@GetMapping("/signin")
    public ResponseEntity<Object> sign(@Valid @RequestParam String token){
	   return this.userServicesImpl.signIn(token);
   }
	@GetMapping("/signOut")
    public ResponseEntity<Object> signOut(@Valid @RequestParam String token){
	   return this.userServicesImpl.signOut(token);
   }
	@GetMapping("/getAllUser")
    public  ResponseEntity<List<User>> getAllUser(@Valid @RequestParam String token){
	   return this.userServicesImpl.getAllUser(token);
   }
	@PutMapping("/update")
    public  ResponseEntity<Object> UpdateUser(@Valid @RequestParam String token,@RequestBody RegisterDto registerDto){
	   return this.userServicesImpl.update(token, registerDto);
   }
	@PutMapping("/deleted/{id}")
    public  ResponseEntity<Object> DeletedUser(@Valid @RequestParam String token,@PathParam("id") int id){
	   return this.userServicesImpl.deletedUser(token, id);
   }
	@GetMapping("/upload")
    public  ResponseEntity<Object>  uploads (@RequestParam("file") MultipartFile multipartFile){
		   FileModel model =this.userServicesImpl.upLoadImage(multipartFile);
		   String url =ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(model.getNameImage()).toUriString();
		   return new ResponseEntity<Object>(url,HttpStatus.OK);
   }
}
