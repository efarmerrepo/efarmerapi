package com.apnafarmers.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.AuthRequest;
import com.apnafarmers.dto.GenericResponse;
import com.apnafarmers.dto.VerifyTokenRequestDTO;
import com.apnafarmers.entity.UserInfo;
import com.apnafarmers.jwt.JwtService;
import com.apnafarmers.service.OtpService;
import com.apnafarmers.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login")
@Slf4j
public class AuthenticationController {

	@Autowired
	private UserService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	OtpService otpService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/user")
	public ResponseEntity<GenericResponse> addNewUser(@RequestBody UserInfo userInfo) {
		UserInfo saveUser = service.saveUser(userInfo);
		String status = null;
		if (saveUser.getId() > 0) {
			status = "success";
		} else {
			status = "failure";
		}

		return new ResponseEntity<>(GenericResponse.builder().status(status).build(), HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<GenericResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		String token = null;

		boolean otpRequired = authRequest.isOtpRequired();
		log.info("otpRequired {} ", otpRequired);
		if (authRequest.isOtpRequired()) {

			Boolean generateOtp = otpService.generateOtp(authRequest);
			if (generateOtp) {
				token = "OTP Send to Mail.";
			}

		} else {
			log.info("Inside Authenticating User using password");
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			if (authentication.isAuthenticated()) {
				token = jwtService.generateToken(authRequest.getUsername());
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		}

		return new ResponseEntity<>(GenericResponse.builder().jwtToken(token).build(), HttpStatus.OK);
	}

	@PostMapping("/validate")
	public ResponseEntity<GenericResponse> validateAndGetToken(@RequestBody VerifyTokenRequestDTO verifyTokenRequest) {

		String jwtToken = "";
		boolean isOtpValid = otpService.validateOTP(verifyTokenRequest);

		if (isOtpValid) {

			Optional<UserInfo> userInfo = service.findByPh(verifyTokenRequest.getPh());

			UserInfo user = userInfo.orElseThrow();
			jwtToken = jwtService.generateToken(user.getName());
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(GenericResponse.builder().jwtToken(jwtToken).build(), HttpStatus.OK);

	}

}
