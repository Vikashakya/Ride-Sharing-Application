package com.cdac.ridesharingapplication.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdac.ridesharingapplication.dto.AuthDriverResponse;
import com.cdac.ridesharingapplication.dto.AuthResponse;
import com.cdac.ridesharingapplication.dto.DriverDTO;
import com.cdac.ridesharingapplication.dto.LoginDTO;
import com.cdac.ridesharingapplication.dto.VehicleDTO;
import com.cdac.ridesharingapplication.exception.CustomException;
import com.cdac.ridesharingapplication.model.Driver;
import com.cdac.ridesharingapplication.model.License;
import com.cdac.ridesharingapplication.model.User;
import com.cdac.ridesharingapplication.model.Vehicle;
import com.cdac.ridesharingapplication.repository.DriverRepository;
import com.cdac.ridesharingapplication.repository.UserRepository;
import com.cdac.ridesharingapplication.security.JwtTokenHelper;

import lombok.AllArgsConstructor;

/**
* Author: Admin
* Date: 6 Feb 2025
**/
@Service
@AllArgsConstructor
public class DriverService {
	private final JwtTokenHelper jwtTokenHelper;
	private final PasswordEncoder passwordEncoder;
    private final DriverRepository driverRepository;
    private final AuthenticationManager authenticationManager;
	public AuthDriverResponse registerUser(DriverDTO driverDTO) {
		 if(driverRepository.existsByEmail(driverDTO.getEmail())) {
	            throw new CustomException("Email already registered!", HttpStatus.CONFLICT);
	        }
	        
	        if(driverRepository.existsByMobile(driverDTO.getMobile())) {
	            throw new CustomException("Mobile number already registered!", HttpStatus.CONFLICT);
	        }
	        Driver driver=new Driver();
	        driver.setEmail(driverDTO.getEmail());
	        driver.setMobile(driverDTO.getMobile());
	        driver.setName(driverDTO.getName());
	        driver.setPassword(passwordEncoder.encode(driverDTO.getPassword()));
	        driver.setLatitude(driverDTO.getLatitude());
	        driver.setLongitude(driverDTO.getLongitude());
	        License license=driverDTO.getLicense();
	        license.setDriver(driver);
	        driver.setLicense(license);
	        Vehicle vehicle=driverDTO.getVehicle();
	        vehicle.setDriver(driver);
	        driver.setVehicle(vehicle);
	        Driver savedDriver=driverRepository.save(driver);
	        
	        return new AuthDriverResponse(
	                "User registered successfully",
	                true,
	                jwtTokenHelper.generateToken(savedDriver.getEmail()), // Replace with actual JWT token
	                savedDriver
	            );
	}

	public AuthDriverResponse loginUser(LoginDTO loginDTO) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword());
   	 try {
   	Authentication authentication = authenticationManager.authenticate(authenticationToken);
   	SecurityContextHolder.getContext().setAuthentication(authentication);
   	 }catch (BadCredentialsException e) {
   	      throw new BadCredentialsException("invalid username or password");
   	    }
   	 Driver driver=driverRepository.findByEmail(loginDTO.getEmail()).orElseThrow(null);

   	    
       return new AuthDriverResponse(
           "Login successful",
           true,
           jwtTokenHelper.generateToken(driver.getEmail()), // Replace with actual JWT token
           driver
       );
	}

	public boolean updateVehicleDetails(String jwtToken, VehicleDTO vehicleDTO) {
		String email=jwtTokenHelper.getUsernameFromToken(jwtToken);
		System.out.println(email);
		Long id=driverRepository.findByEmail(email).orElseGet(null).getVehicle().getId();
		
		int p=driverRepository.updateVehicle(id, vehicleDTO.getColor(),vehicleDTO.getCompany(),vehicleDTO.getLicensePlate(),vehicleDTO.getModel(),vehicleDTO.getYear(),vehicleDTO.getCapacity());
		return p>0;
	}


}
