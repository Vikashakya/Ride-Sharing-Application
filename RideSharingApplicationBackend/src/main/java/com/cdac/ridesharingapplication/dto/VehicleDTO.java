package com.cdac.ridesharingapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Author: Admin
* Date: 7 Feb 2025
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
	private String company;
    private String model;
    private String color;
    private Integer year;
    private String licensePlate;
    private Integer capacity;

}
