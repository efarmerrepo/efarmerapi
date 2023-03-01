package com.apnafarmers.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.UserInfo;
import com.apnafarmers.repository.UserInfoRepository;

@Service
public class UserService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserInfo saveUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		UserInfo save = userInfoRepository.save(userInfo);
		return save;
	}

	public Optional<UserInfo> findByPh(String ph) {
		Optional<UserInfo> findByPh = userInfoRepository.findByPh(ph);
		return findByPh;
	}
	
	public Optional<UserInfo> findByUserName(String ph) {
		Optional<UserInfo> findByName = userInfoRepository.findByName(ph);
		return findByName;
	}


}
