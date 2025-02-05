package com.cdac.ridesharingapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdac.ridesharingapplication.dto.AuthResponse;
import com.cdac.ridesharingapplication.dto.LoginDTO;
import com.cdac.ridesharingapplication.dto.UserDTO;
import com.cdac.ridesharingapplication.exception.CustomException;
import com.cdac.ridesharingapplication.model.User;
import com.cdac.ridesharingapplication.repository.UserRepository;
import com.cdac.ridesharingapplication.security.JwtTokenHelper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	 
	private final JwtTokenHelper jwtTokenHelper;
	private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    
    
    
    public AuthResponse registerUser(UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException("Email already registered!", HttpStatus.CONFLICT);
        }
        
        if(userRepository.existsByMobile(userDTO.getMobile())) {
            throw new CustomException("Mobile number already registered!", HttpStatus.CONFLICT);
        }
        
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Should be encrypted in production
        user.setProfilePicture(userDTO.getProfilePicture());
        System.out.println(user.getEmail());
        User savedUser = userRepository.save(user);
        
        return new AuthResponse(
            "User registered successfully",
            true,
            jwtTokenHelper.generateToken(savedUser.getEmail()), // Replace with actual JWT token
            savedUser
        );
    }
    
    public AuthResponse loginUser(LoginDTO loginDTO) {
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword());
    	 try {
    	Authentication authentication = authenticationManager.authenticate(authenticationToken);
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	 }catch (BadCredentialsException e) {
    	      throw new BadCredentialsException("invalid username or password");
    	    }
    	 User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(null);

    	    
        return new AuthResponse(
            "Login successful",
            true,
            jwtTokenHelper.generateToken(user.getEmail()), // Replace with actual JWT token
            user
        );
    }
}
