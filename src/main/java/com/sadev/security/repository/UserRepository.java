package com.sadev.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadev.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
