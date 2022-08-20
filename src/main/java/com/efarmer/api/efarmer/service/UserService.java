package com.efarmer.api.efarmer.service;

import java.util.Optional;

import com.efarmer.api.efarmer.entity.UserEntity;

public interface UserService {

	Optional<UserEntity>  findUserById(Long id);

	UserEntity save(UserEntity userEntity);

}
