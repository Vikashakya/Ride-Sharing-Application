package com.cdac.ridesharingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cdac.ridesharingapplication.dto.VehicleDTO;
import com.cdac.ridesharingapplication.model.Driver;
import com.cdac.ridesharingapplication.model.User;

import jakarta.transaction.Transactional;

/**
* Author: Admin
* Date: 6 Feb 2025
**/
public interface DriverRepository  extends JpaRepository<Driver, Long>{

	Optional<Driver> findByEmail(String username);
	boolean existsByEmail(String email);
	boolean existsByMobile(String mobile);
	//hello
	@Transactional
	@Modifying
    @Query("update Vehicle v set v.color = ?2, v.company = ?3, v.licensePlate = ?4, v.model = ?5, v.year = ?6, v.capacity=?7 where v.id = ?1")
	int updateVehicle(Long id, String color, String company, String licensePlate, String model, Integer year,
			Integer capacity);
	

}
