package com.cdac.ridesharingapplication.dto;

import com.cdac.ridesharingapplication.model.Driver;
import com.cdac.ridesharingapplication.model.User;

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
public class AuthDriverResponse {
	private String message;
    private boolean success;
    private String token;
    private Driver driver;
}
