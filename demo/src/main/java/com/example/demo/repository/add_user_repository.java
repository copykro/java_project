package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserDetails;

public interface add_user_repository extends JpaRepository<UserDetails,Integer>{

}
