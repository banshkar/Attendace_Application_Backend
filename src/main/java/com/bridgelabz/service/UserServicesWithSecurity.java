package com.bridgelabz.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.RegisterDto;
import com.bridgelabz.dto.Testing;
import com.bridgelabz.exception.ResponceHandle;
import com.bridgelabz.exception.Userexception;
import com.bridgelabz.model.AttendanceModel;
import com.bridgelabz.model.DataStore;
import com.bridgelabz.model.FileModel;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.ImageRepo;
import com.bridgelabz.repo.UserRepo;
import com.bridgelabz.utility.TokenManager;



@Service
public class UserServicesWithSecurity implements UserServiceWIthAop{
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TokenManager tokenManager;
    @Autowired
	private ModelMapper mapper;
    @Autowired
    private ResponceHandle reposHandle;
    
    @Autowired
    private ImageRepo imageRepo;
    
    @Autowired
    private Testing testing;
    
  
    Logger logger =LoggerFactory.getLogger(UserServicesWithSecurity.class);
	@Override
	public ResponseEntity<Object> rigster(RegisterDto registerDto) {
		if(userRepo.findByEmail(registerDto.getEmail()).isPresent()){
			if(userRepo.findByUsername(registerDto.getUsername()).isPresent()) {
				logger.error("This UserName Already Register");
				
				throw new Userexception("This UserName Already Register ");
			}
			logger.error("Your Email Already Register");
			throw new Userexception("Your Email Already Register");
		}
		User user =mapper.map(registerDto, User.class);
		 DataStore dataStore =new DataStore();
		 user.setDataStore(dataStore);
		this.userRepo.save(user);
		logger.info("Sucessfully execution : {}", registerDto);
		return reposHandle.response("Register SuccessFully", registerDto);
	}
	
	@Override
	public ResponseEntity<List<User>> getAllUser(String token) {
		String email = tokenManager.decodeToken(token);
		if(userRepo.findByEmail( email ).get().isLogin()) {
			if(userRepo.findByEmail( email ).get().getRoll().equals("admin")) {
				List<User> userList =userRepo.findAll();
				logger.info("Sucessfully execution : {}", userList);
				return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
			}
			logger.error("you are not Admin");
			throw new Userexception("you are not Admin");
		}
		logger.error("Please Login");
		throw new Userexception("Please Login");
		
		
	}
	
  
	@Override
	public ResponseEntity<Object> loginUser(LoginDto loginDto) {
		if(userRepo.findByUsername(loginDto.getUsername()).isPresent()) {
			if(userRepo.findByUsername(loginDto.getUsername()).get().getPassword().equals(loginDto.getPassword())) {
				  String token =tokenManager.generateToken(userRepo.findByUsername(loginDto.getUsername()).get().getEmail());
								  User user =userRepo.findByUsername(loginDto.getUsername()).get();
								  user.setLogin(true);
								  this.userRepo.save(user);
									logger.info("Sucessfully execution : {}", user);
								  return reposHandle.response("...Login...", token);
							}
							else {
								logger.info("Sucessfully execution :Please Enter Right Password ");
								return new ResponseEntity<Object>("Please Enter Right Password ",HttpStatus.NOT_FOUND);
							}
		}
		logger.error("Please Register Account After You can Login Byy");
		throw new Userexception("Please Register Account After You can Login Byy");
	}
	@Override
	public ResponseEntity<Object> getDetails(String token) {
		String email =tokenManager.decodeToken(token);
		if(userRepo.findByEmail( email).isPresent()) {
		     if(userRepo.findByEmail(email).get().isLogin()) {
		    		User user =userRepo.findByEmail( email).get();
					 RegisterDto registerDto =mapper.map(user, RegisterDto.class);
						logger.info("Sucessfully execution : {}", registerDto);
					  return reposHandle.response("Your details", registerDto);
		     }
		     logger.error("Please Login Your  Account..");
		     throw new Userexception("Please Login Your  Account..");
			
		}
		logger.error("Please Enter right Information");
		throw new Userexception("Please Enter right Information");
	}
	@Override
	public ResponseEntity<Object> LogOut(String token) {
		String email =tokenManager.decodeToken(token);
		if(userRepo.findByEmail( email).isPresent()) {
			if(userRepo.findByEmail( email).get().isLogin() ) {
				User user =userRepo.findByEmail( email).get();
				  user.setLogin(false);
				  this.userRepo.save(user);
					logger.info("Sucessfully execution :LogOut Your Account ");
				  return new ResponseEntity<Object>("LogOut Your Account",HttpStatus.OK);
			}
			logger.error("LogOut Your Accout");
			throw new Userexception("LogOut Your Accout");
			
		}
		logger.error("Please Enter right Information");
		throw new Userexception("Please Enter right Information");
	}
	@Override
	public ResponseEntity<List<AttendanceModel>> getAttendanceReport(String token) {
		String email =tokenManager.decodeToken(token);
		if(userRepo.findByEmail( email).isPresent()) {
			User user =userRepo.findByEmail( email).get();
			List<AttendanceModel> list = user.getDataStore().getAttendanceModels();
			logger.info("Sucessfully execution :{}",list);
			return new ResponseEntity<List<AttendanceModel>>(list,HttpStatus.OK);
		}
		logger.error("Please Enter right Information");
		throw new Userexception("Please Enter right Information");
	}
//	@Scheduled(fixedRate =3,timeUnit = TimeUnit.SECONDS)
//	public void  updateAttendanceMark() {
//		int m=1;
//             if(m==1) {
//            	 
//            	  List<User> users =userRepo.findAll();
//                  List<User>sortedList =users.stream().filter(e->e.getSigInTime().equals("Null")).collect(Collectors.toList());
//                  Iterator<User> itr =sortedList.iterator();
//                 logger.info(sortedList.toString());
//                  Iterator<User> itrSecond =users.iterator();
//                 while(itr.hasNext()) {
//               	  User user =itr.next();
//               	  List<AttendanceModel> attendanceModels =user.getDataStore().getAttendanceModels();
//               	    String date = String.valueOf(LocalDate.now());
//               	  AttendanceModel attendanceModel =new AttendanceModel();
//               	  attendanceModel.setDate(date);
//               	  attendanceModel.setAttendancemark("Absent");
//               	  attendanceModel.setSignIn("NULL");
//               	  attendanceModel.setSignOut("NULL");
//               	  attendanceModels.add(attendanceModel);
//               	  user.getDataStore().setAttendanceModels(attendanceModels);
//               	  user.setDate("Null");
//               	  user.setSigInTime("Null");
//               	  user.setSignOutTime("Null");
//               	   user.setAttendanceMark("absent");
//               	  this.userRepo.save(user);}
//                 
//              while(itrSecond.hasNext()) {
//            	  User user =itrSecond.next();
//            	   user.setAttendanceMark("absent");
//            	   user.setDate("Null");
//            	   user.setSigInTime("Null");
//            	   user.setSignOutTime("Null");
//            	   this.userRepo.save(user);
//              }
//                 
//             }
//             else {
//            	 logger.info("No Update Time Attendace");
//             }
//                 
//                
//	}


	@Override
	public ResponseEntity<Object> signIn(String token) {
		String email =tokenManager.decodeToken(token);
		if(userRepo.findByEmail( email).isPresent()) {
			  if(userRepo.findByEmail( email).get().isLogin()) {
				          String date = String.valueOf(LocalDate.now());
						  String time  = String.valueOf(LocalTime.now());
						  User user =userRepo.findByEmail( email).get();
						  user.setAttendanceMark("Present");
						  user.setDate(date);
						  user.setSigInTime(time);
						  user.setLogin(true);
						  user.setSignOutTime("Working...");
						  this.userRepo.save(user);
						  logger.info("Sucessfully execution : Signin");
						  return reposHandle.response("Sigin", time);
					}
			  else { 
				  logger.error("Please Login");
					throw new Userexception("Please Login");
			  }
				
		}
		logger.error("Please Enter right Information");
		throw new Userexception("Please Enter right Information");
	}
	@Override
	public ResponseEntity<Object> signOut(String token) {
		String email =tokenManager.decodeToken(token);
		if(userRepo.findByEmail( email).isPresent()) {
			if(userRepo.findByEmail( email).get().isLogin() ) {
				if(userRepo.findByEmail( email).get().getAttendanceMark().equals("Present")) {
					User user =userRepo.findByEmail( email).get();
					  String time  = String.valueOf(LocalTime.now());
					  AttendanceModel attendanceModel =new AttendanceModel();
					  attendanceModel.setSignIn(user.getSigInTime());
					  attendanceModel.setSignOut(time);
					  attendanceModel.setAttendancemark("present");
					  user.getDataStore().getAttendanceModels().add(attendanceModel);
					  user.setAttendanceMark("absent");
					  user.setSignOutTime(time);
					  this.userRepo.save(user);
					  logger.info("Sucessfully execution : SigngOut Your Account");
					  return new ResponseEntity<Object>("SigngOut Your Account",HttpStatus.OK);
				}
				logger.error("you Already SignOut");
				throw new Userexception("you Already SignOut");
			}
			logger.error("Your Login  Account");
			throw new Userexception(" Your Login  Account");
			
		}
		logger.error("Please Enter right Information");
		throw new Userexception("Please Enter right Information");
	}
	@Override
	public ResponseEntity<Object> update(String token,RegisterDto registerDto) {
		String email =tokenManager.decodeToken(token);
		if(userRepo.findByEmail( email).isPresent()) {
	      if(userRepo.findByEmail( email).get().isLogin()) {
	    	  User user =userRepo.findByEmail( email).get();
	    	  user.setDataStore(user.getDataStore());
	    	  user.setId(user.getId());
	    	  if(registerDto.getUsername()==null) {
	    		  user.setUsername(user.getUsername());
	    	  }
	    	  else if(registerDto.getUsername()!=null) {
	    		  user.setUsername(registerDto.getUsername());
	    	  }
	    	  if(registerDto.getEmail()==null) {
	    		  user.setUsername(user.getEmail());
	    	  }
	    	  else if(registerDto.getEmail()!=null) {
	    		  user.setUsername(registerDto.getEmail());
	    	  }
	    	  if(registerDto.getPassword()==null) {
	    		  user.setUsername(user.getPassword());
	    	  }
	    	  else if(registerDto.getPassword()!=null) {
	    		  user.setUsername(registerDto.getPassword());
	    	  }
	    	  if(registerDto.getPhoneNumber()==null) {
	    		  user.setUsername(user.getPhoneNumber());
	    	  }
	    	  else if(registerDto.getUsername()!=null) {
	    		  user.setUsername(registerDto.getPhoneNumber());
	    	  }
	    	  this.userRepo.save(user);
	    	  logger.info("Sucessfully execution : Update Details SuccessFully");
	    	 return reposHandle.response("Update Details SuccessFully", registerDto);
	    	  
	      }
	    	  throw new Userexception("Please Login");
	      
		}
		throw new Userexception("Please Enter right Information");
	}

	@Override
	public ResponseEntity<Object> deletedUser(String token, int id) {
		String email = tokenManager.decodeToken(token);
		if(userRepo.findByEmail( email).get().isLogin()) {
			if(userRepo.findByEmail( email).get().getRoll().equals("admin")) {
			      User user =userRepo.findById(id).get();
			      RegisterDto registerDto =mapper.map(user, RegisterDto.class);
			      this.userRepo.deleteById(id);
			      logger.info("Sucessfully execution : Deleted User Successfully");
			      return reposHandle.response("Deleted User Successfully", registerDto);
			}
			logger.error("you are not Admin");
			throw new Userexception("you are not Admin");
		}
		logger.error("Please Login");
		throw new Userexception("Please Login");
	}


	@Override
	public ResponseEntity<Object> test(
			) {
		return new ResponseEntity<Object>("Workingg testing",HttpStatus.OK);
	}

	@Override
	public FileModel upLoadImage(MultipartFile multipartFile) {
		String filename =multipartFile.getOriginalFilename();
		
		  try {
			  if(filename.contains("..")) {
				  throw new Userexception("Filename Containe Path Invalid.. Sorry UpLoad Right");
			  }
			 FileModel model =new FileModel(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
			 this.imageRepo.save(model);
			 logger.info("Sucessfully execution : Upload File Successfully");
			 return model;
		  }
		  catch (Exception e) {
			throw new Userexception("could not upload file");
		}
	
	}

	@Override
	public ResponseEntity<Object> download(int id) {
		FileModel model =imageRepo.findById(id).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(model.getImageType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + model.getNameImage() + "\"").body(new ByteArrayResource(model.getData()));
	}


	
		 
}
