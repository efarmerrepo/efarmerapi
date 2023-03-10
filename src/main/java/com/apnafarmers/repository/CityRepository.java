package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apnafarmers.entity.City;

public interface CityRepository  extends JpaRepository<City, Long>  {

}
