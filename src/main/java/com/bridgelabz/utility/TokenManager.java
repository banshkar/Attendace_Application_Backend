package com.bridgelabz.utility;

import java.io.Serializable;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bridgelabz.dto.LoginDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  String  secretKey ="kljhgfdssazxcvbnmpoiuytrewq";
	
	 public String  generateToken(String email) {
		 Map<String,Object> claim = new HashMap<>();
		 claim.put("email",email);
	
		 String  token =Jwts.builder().setClaims(claim).setSubject(email).signWith(SignatureAlgorithm.HS256,secretKey).compact();
		 return token;
	 }
	 public String decodeToken(String token) {
		 final Claims claims =Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		  String email =(String) claims.get("email");
		   return email;
		 
	 }
	
	}
	


