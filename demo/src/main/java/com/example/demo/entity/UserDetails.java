package com.example.demo.entity;

import java.util.Collection;

import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user_Dtl")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="firstName")
	@NotBlank(message = "First name is required")
    @Size(max = 50, min= 2, message = "First name cannot exceed 50 characters")
	@Pattern(regexp = "^[A-Za-z]{3,50}$", message = "First name sould be characters only")
	private String firstName;
	
	@Column(name="lastName")
	@NotBlank(message = "Last name is required")
	@Size(max = 50, min= 2, message = "Last name cannot exceed 50 characters")
	@Pattern(regexp = "^[A-Za-z]{3,50}$", message = "Last name sould be characters only")
	private String lastName;
	
	@Column(name="dob")
	@NotBlank(message = "DoB an not be empty")
	private String dob;
	
	@Column(name="gender")
	private String gender;
	
	
	@Email
	@NotBlank(message = "Email can not be blank")
	@Column(name="email", unique = true)	
	private String email;
	
	@Column(name="password")
	//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
	@Size(max = 50, min= 2, message = "Password must be at least 5 characters long.")
	private String password;
	
	@Column(name="mobileNumber")
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
	private String mobileNumber;

	@ManyToMany(fetch=FetchType.EAGER, cascade=jakarta.persistence.CascadeType.ALL)
	@JoinTable(name="users_roles",
	           joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
	           inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="Id")
			)
	private Collection<Roles> roles;
//	@ManyToOne
//	Roles roles;
	
//    public String getRecaptchaResponse() {
//		return recaptchaResponse;
//	}
//
//	public void setRecaptchaResponse(String recaptchaResponse) {
//		this.recaptchaResponse = recaptchaResponse;
//	}
//
//	private String recaptchaResponse;

	public Collection<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
	}

	public String getMobileNumber() {
	    return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
	    this.mobileNumber = mobileNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gender=" + gender + ", email=" + email + ", password=" + password + "]";
	}
}
