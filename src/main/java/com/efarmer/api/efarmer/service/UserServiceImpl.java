package com.efarmer.api.efarmer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efarmer.api.efarmer.entity.UserEntity;
import com.efarmer.api.efarmer.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<UserEntity> findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserEntity save(UserEntity userEntity) {
		return userRepository.save(userEntity);
		
	}

}
