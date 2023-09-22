package com.mycompany.mycompany.entites;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity

public class Office {

	@Id
    
    private Long id;
    private String address;
    private String country;
	public Office() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Office(Long id, String address, String country) {
		super();
		this.id = id;
		this.address = address;
		this.country = country;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
    
   
}
