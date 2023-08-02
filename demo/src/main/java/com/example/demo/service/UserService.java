package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserDetails;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	private UserRepository user_Repository;
	@Autowired
    public UserService(UserRepository userRepository) {
        this.user_Repository = user_Repository;
    }
	
    public void updateUser(UserDetails user) {
        // Retrieve the user from the database using the ID
    	UserDetails existingUser = new UserDetails();
        
        if (existingUser != null) {
            // Update the user details
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            
            // Save the updated user to the database
          
	
						user_Repository.save(existingUser);
					   
			
		
        }
}
    public List<UserDetails> getAllUsers() {
        return user_Repository.findAll();
    }
    
//    public List<UserDetails> saveAll() {
//        return user_Repository.saveAll();
//    }
}
