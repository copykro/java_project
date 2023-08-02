package com.example.demo.repository;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>{
 
	//@Query("SELECT r FROM Roles r")
  //  List<Roles> findAllRoles();

	//void deleteById(Long id);

}
