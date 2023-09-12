package com.sadev.security.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadev.security.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
