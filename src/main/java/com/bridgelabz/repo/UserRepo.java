package com.bridgelabz.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	 public Optional<User> findByEmail(String email); 
	 public Optional<User> findByUsername(String userName); 
	 public Optional<User> findBySigInTime(String sigintime);

}
