package com.cdac.ridesharingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.ridesharingapplication.dto.AuthDriverResponse;
import com.cdac.ridesharingapplication.dto.AuthResponse;
import com.cdac.ridesharingapplication.dto.DriverDTO;
import com.cdac.ridesharingapplication.dto.LoginDTO;
import com.cdac.ridesharingapplication.dto.UserDTO;
import com.cdac.ridesharingapplication.dto.VehicleDTO;
import com.cdac.ridesharingapplication.service.DriverService;

/**
* Author: Admin
* Date: 30 Jan 2025
**/
@RestController
@RequestMapping("/driver")
public class DriverController {
	  @Autowired
      private  DriverService driverService;
	 @PostMapping("/register")
	    public ResponseEntity<AuthDriverResponse> registerUser(@RequestBody DriverDTO userDTO) {
	    	System.out.println("register");
	    	AuthDriverResponse response = driverService.registerUser(userDTO);
	        return ResponseEntity.ok(response);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<AuthDriverResponse> loginUser(@RequestBody LoginDTO loginDTO) {
	    	System.out.println("login");
	    	AuthDriverResponse response = driverService.loginUser(loginDTO);
	        return ResponseEntity.ok(response);
	    }
	    @GetMapping("/data")
	    public ResponseEntity<String> data()
	    {
	    	return ResponseEntity.ok("hello");
	    }
	    
	    @PutMapping("/update-vehicle")
	    public ResponseEntity<String> updateVehicle(@RequestHeader("Authorization") String jwtToken,@RequestBody VehicleDTO vehicleDTO) {
	        System.out.println("update-vehicle");
	        boolean isUpdated = driverService.updateVehicleDetails(jwtToken,vehicleDTO);
	        
	        if (isUpdated) {
	            return ResponseEntity.ok("Vehicle details updated successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update vehicle details");
	        }
	    }
	    
}
