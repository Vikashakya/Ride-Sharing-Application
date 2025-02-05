package com.cdac.ridesharingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.ridesharingapplication.model.Driver;
import com.cdac.ridesharingapplication.model.User;

/**
* Author: Admin
* Date: 6 Feb 2025
**/
public interface DriverRepository  extends JpaRepository<Driver, Long>{

	Optional<Driver> findByEmail(String username);

}
