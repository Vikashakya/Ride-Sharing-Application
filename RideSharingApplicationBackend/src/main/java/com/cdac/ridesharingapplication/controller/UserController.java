package com.cdac.ridesharingapplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cdac.ridesharingapplication.model.User;
import com.cdac.ridesharingapplication.service.UserService;
import com.cdac.ridesharingapplication.dto.UserDTO;
import com.cdac.ridesharingapplication.dto.AuthResponse;
import com.cdac.ridesharingapplication.dto.LoginDTO;

@RestController
@RequestMapping("/user")
public class UserController 
{
	    @Autowired
	    private UserService userService;
	    
	    @PostMapping("/register")
	    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserDTO userDTO) {
	    	System.out.println("register");
	        AuthResponse response = userService.registerUser(userDTO);
	        return ResponseEntity.ok(response);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginDTO loginDTO) {
	    	System.out.println("login");
	        AuthResponse response = userService.loginUser(loginDTO);
	        return ResponseEntity.ok(response);
	    }
	    @GetMapping("/data")
	    public ResponseEntity<List<String>> data()
	    {   System.out.println("data");
	    	List<String> l=new ArrayList<>();
	      l.add("hello");
	    	return ResponseEntity.ok(l);
	    }
}
