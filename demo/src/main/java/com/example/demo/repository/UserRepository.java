package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserDetails;

import jakarta.validation.Valid;

@Repository
public interface UserRepository extends JpaRepository<UserDetails,Long> {

	UserDetails findByEmail(String email);
    List<UserDetails> findAll();
	//List<UserDetails> saveAll();
    
	



}
