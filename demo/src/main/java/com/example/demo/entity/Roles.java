package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Roles")
public class Roles {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	@Column(name="Id")
	private int Id;
	
	@Column(name="Role")
	private String Role;
	
	@Column(name="Description")
	private String Description;
	
	//@OneToMany(mappedBy = "Roles")
   // private List<UserDetails> userDetails;

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String Role) {
		this.Role = Role;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	@Override
	public String toString() {
		return "Roles [Id=" + Id + ", Role=" + Role + ", Description=" + Description + "]";
	}


    

	
}
