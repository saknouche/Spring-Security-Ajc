package com.sadev.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadev.security.model.User;
import com.sadev.security.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
        return ResponseEntity.status(HttpStatus.OK).body(users);

	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
		
		Optional<User> optUser = userRepository.findById(id);
		if(optUser.isPresent()) {
			User user = optUser.get();
		return ResponseEntity.status(HttpStatus.OK).body(user);
		}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
	
	
}
