package com.sadev.security.controller;

import java.util.ArrayList;
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

import com.sadev.security.dto.UserDtoResponse;
import com.sadev.security.model.User;
import com.sadev.security.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDtoResponse>> getAllUsers(){
		
		List<User> users = userRepository.findAll();
		List<UserDtoResponse> usersDto = new ArrayList<>();
		
		if(users.isEmpty()) {			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		for (User user : users) {
			usersDto.add(new UserDtoResponse(user.getId(), user.getUsername()));
		}
        return ResponseEntity.status(HttpStatus.OK).body(usersDto);

	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDtoResponse> getUserById(@PathVariable("id") Long id){
		
		Optional<User> optUser = userRepository.findById(id);
		if(optUser.isPresent()) {
			User user = optUser.get();
			new UserDtoResponse(user.getId(), user.getUsername());
		return ResponseEntity.status(HttpStatus.OK).body(new UserDtoResponse(user.getId(), user.getUsername()));
		}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
	
	
}
