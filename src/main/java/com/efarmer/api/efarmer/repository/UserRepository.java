package com.efarmer.api.efarmer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efarmer.api.efarmer.entity.UserEntity;


public interface UserRepository  extends JpaRepository<UserEntity, Long> {

}