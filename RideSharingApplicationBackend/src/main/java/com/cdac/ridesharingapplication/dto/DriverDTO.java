package com.cdac.ridesharingapplication.dto;

import com.cdac.ridesharingapplication.model.License;
import com.cdac.ridesharingapplication.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Author: Admin
* Date: 6 Feb 2025
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
	 private String name;
	  private String email;
	  private String password;
	  private String mobile;
	  private double latitude;
	  private double longitude;
	  private License license;
	  private Vehicle vehicle;
}
