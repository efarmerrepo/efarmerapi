package com.efarmer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efarmer.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {



}
