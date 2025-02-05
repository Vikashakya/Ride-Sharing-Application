package com.cdac.ridesharingapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdac.ridesharingapplication.model.Driver;
import com.cdac.ridesharingapplication.model.User;
import com.cdac.ridesharingapplication.repository.DriverRepository;
import com.cdac.ridesharingapplication.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
* Author: Admin
* Date: 6 Feb 2025
**/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService{
	
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).orElse(null);
	    if (user != null)
	     return buildUserDetails(user.getEmail(), user.getPassword());
	    Driver driver = driverRepository.findByEmail(username).orElse(null);
	    if (driver != null)
	      return buildUserDetails(driver.getEmail(), driver.getPassword());
	    throw new UsernameNotFoundException("User or Driver not found");
		
	}
	private UserDetails buildUserDetails(String username, String password) {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	        return new org.springframework.security.core.userdetails.User(username, password, authorities);
	  }

	

}
