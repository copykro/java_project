package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.RecaptchaResponse;
import com.example.demo.entity.OTP;
import com.example.demo.entity.Roles;
import com.example.demo.entity.UserDetails;

import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.add_user_repository;
import com.example.demo.service.OTPService;
import com.example.demo.service.UserService;
import com.example.demo.util.OTPGenerator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Controller
public class UserController {

	@Autowired
	private UserRepository user_Repository;
   
	@Autowired
	private RoleRepository repository;
	

	@GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("user", new UserDetails());
        return "index";
    }
	


	@PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserDetails userDetails, BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model, HttpSession session, HttpServletRequest request) {

		//System.out.println("Step1");
	
		//System.out.println(userDetails);
		
		if (bindingResult.hasErrors()) {	
			// System.out.println("failed in validation");
			 model.addAttribute("user",  userDetails);
			 model.addAttribute("error", "please fill details in correct format");
	        return "index";
	    }
		//System.out.println("Step3");
       // model.addAttribute("success", "User registered successfully");
        redirectAttributes.addFlashAttribute("success", "User registered successfully");
        
		user_Repository.save(userDetails);		
	
	    return "redirect:/"; 
    }


	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
	    model.addAttribute("user", new UserDetails());
	    model.addAttribute("loginUser", new UserDetails());
	    return "login";
	}
	
//	@PostMapping("/login")
//	public String login(@ModelAttribute("user") UserDetails userDetails, HttpSession session, Model model) {
//	    String email = userDetails.getEmail();
//	    String password = userDetails.getPassword();
//	    // Retrieve the user from the database based on the entered email
//	    UserDetails user = user_Repository.findByEmail(email);
//
//	    if (user != null && user.getPassword().equals(password)) {
//	        session.setAttribute("user", user);
//	        return "user-management";
//	    } else {
//	        model.addAttribute("error", "Invalid email or password");
//	        return "login";
//	    }
//	}
//	
	@GetMapping("/verify-otp")
	public String verifyOTP(Model model) {
	    model.addAttribute("otp", new OTP());
	    return "verify-otp";
	}
	
//	@PostMapping("/verify-otp")
//	public String verifyOTP(@ModelAttribute("otp") OTP otp, HttpSession session, Model model) {
//	    String sessionOTP = (String) session.getAttribute("otp");
//	    if (sessionOTP != null && sessionOTP.equals(otp.getCode())) {
//	        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
//	        repo.save(userDetails);
//	        session.removeAttribute("otp");
//	        session.removeAttribute("userDetails");
//	        session.setAttribute("message", "User registered successfully");
//	        return "redirect:/"; 
//	    } else {
//	        model.addAttribute("error", "Invalid OTP");
//	        return "verify-otp";
//	    }

//}
	
//	 private boolean verifyRecaptcha(String recaptchaResponse) {
//	        String url = "https://www.google.com/recaptcha/api/siteverify";
//	        RestTemplate restTemplate = new RestTemplate();
//
//	        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//	        map.add("secret", recaptchaSecretKey);
//	        map.add("response", recaptchaResponse);
//
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//	        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
//
//	        RecaptchaResponse response = restTemplate.postForObject(url, entity, RecaptchaResponse.class);
//
//	        return response != null && response.isSuccess();
//	    }
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session to log the user out
        return "redirect:/"; // Redirect to the homepage or login page
    }
	
	@GetMapping("/welcome")
	public String welcomePage(Model model, Principal principal) {
	    if (principal != null) {
	        model.addAttribute("authenticated", true);
	        model.addAttribute("user", user_Repository.findByEmail(principal.getName()));
	    } else {
	        model.addAttribute("authenticated", false);
	    }
	    return "welcome";
	}

	
	
	
	 @PostMapping("/welcome")
	 public String welcomePage(@ModelAttribute("loginUser") UserDetails loginUser, HttpSession session, Model model) {
	        // Retrieve the user from the database based on the entered email
	        String email = loginUser.getEmail();
	        String password = loginUser.getPassword();
	        UserDetails user = user_Repository.findByEmail(email);

	        if (user != null && user.getPassword().equals(password)) {
	            session.setAttribute("user", user);
	            model.addAttribute("user", user);
	            model.addAttribute("authenticated", true);
	            return "welcome";
	        } else {
	            model.addAttribute("error", "Invalid email or password");
	            return "index"; // Redirect back to the index page with the error message
	        }
	    }
	 
	@GetMapping("/list-users")
    public String listUsers(Model model) {
        List<UserDetails> users = user_Repository.findAll();
        model.addAttribute("users", users);
        return "list-users";
    }
	
	
	@GetMapping("/Users")
	public String view() {
		
		return "user-management";
	}
	
	@PostMapping("/Users")
	public String show() {
		
		return "user-management";
	}
	
	 @GetMapping("/edit_user")
	    public String editUserPage(Model model) {
		 List<UserDetails> users = user_Repository.findAll(); // Retrieve the list of users from the service
		    model.addAttribute("users", users);
	        return "edit_user";
	    }
	 
	 @PostMapping("/users/edit")
	    public String editUser(@ModelAttribute("user") UserDetails u) {
	
		 user_Repository.save(u);
	 
	        return "redirect:/list-users"; // Redirect to the list of users page
	    }
	 
	 @GetMapping("/Roles")
	  public String getRole(Model model) {
		 Roles adminRole = new Roles();
	        adminRole.setId(1);
	        adminRole.setRole("Admin");
	        adminRole.setDescription("Administrator role ");
	        
	        Roles userRole = new Roles();
	        userRole.setId(2);
	        userRole.setRole("User");
	        userRole.setDescription("Standard user role");
	        
	        Roles superAdminRole = new Roles();
	        superAdminRole.setId(3);
	        superAdminRole.setRole("Super Admin");
	        superAdminRole.setDescription("Administrator role with full access");
	      model.addAttribute("Roles", List.of(adminRole, userRole, superAdminRole)) ; 
	      repository.saveAll(List.of(adminRole, userRole, superAdminRole)); 
		
//		 List<Roles> roles = repository.findAllRoles();
//	        model.addAttribute("roles", roles);
		 return "Roles" ;
		  
	  }
	
	 @PostMapping("/Roles")
	 public String addRole() {

		 
//		 Roles newRole = new Roles();
//	        newRole.setRole("New Role");
//	        newRole.setDescription("Description of the new role");
	     //   repository.save(newRole);
	     return "redirect:/Roles";
	 }
	 
	 @GetMapping("/adminPermission")
	 public String getRolePermission() {
		return "adminPermission";
		 
	 }
	 @PostMapping("/roles/delete/{id}")
	    public String deleteRole(@PathVariable("id") Long id) {
	   
	 //       repository.deleteById(id);

	        return "redirect:/Roles";
	    }

	public String Success(@ModelAttribute UserDetails u, HttpSession session) {
		
		return "redirect:/";
	}
	
}
