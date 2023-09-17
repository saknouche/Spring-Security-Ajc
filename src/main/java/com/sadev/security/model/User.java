package com.sadev.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Role> roleList = new ArrayList<>();
	
	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	//UserDetails
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Boucler sur la liste de roles
		//créer une liste contenant plusieurs simpleFrantedAuthority
		//retourner cette liste de simpleFrantedAuthority
//		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//		for (Role role : roleList) {
//			SimpleGrantedAuthority simpleGrantedAuthority =  new SimpleGrantedAuthority(role.getName());
//			simpleGrantedAuthorities.add(simpleGrantedAuthority);
//		}
//		
//		return simpleGrantedAuthorities; 
		//Version JAVA 8
		return this.roleList
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()))
					.toList();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	
	
	//getters et setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roles) {
		this.roleList = roles;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}


	

}
