package com.cdac.ridesharingapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Author: Admin
* Date: 6 Feb 2025
**/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String company;
	    private String model;
	    private String color;
	    private Integer year;
	    private String licensePlate;
	    private Integer capacity;

	    @JsonIgnore
	    @OneToOne(cascade = CascadeType.ALL)
	    private Driver driver;
}
