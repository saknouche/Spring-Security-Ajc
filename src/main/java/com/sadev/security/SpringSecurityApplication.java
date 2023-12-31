package com.sadev.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//import java.util.Arrays;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sadev.security.model.Role;
import com.sadev.security.model.Role;
import com.sadev.security.model.User;
import com.sadev.security.repository.UserRepository;

//import com.sadev.security.controller.RoleRepository;
//import com.sadev.security.model.Role;
//import com.sadev.security.model.User;
//import com.sadev.security.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
//	@Autowired
//	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setUsername("sadev");
//		user.setPassword(passwordEncoder.encode("root"));
//		user.setRoleList(Arrays.asList(new Role("Moderateur")));
//		Optional<User> optUser = userRepository.findById((long) 1);
//		User user = optUser.get();
//		System.out.println(user);
//		Optional<Role> optRole = roleRepository.findById((long) 2);
//		Role role = optRole.get();
//		System.out.println(role);
//		user.setRoles(Arrays.asList(role));
//		userRepository.save(user);
	}

}
