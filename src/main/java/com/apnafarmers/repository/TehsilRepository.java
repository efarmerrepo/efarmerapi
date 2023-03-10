package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.Tehsil;

@Repository
public interface TehsilRepository extends JpaRepository<Tehsil, Long>  {

}
