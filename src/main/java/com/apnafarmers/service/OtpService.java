package com.apnafarmers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.AuthRequest;
import com.apnafarmers.dto.EmailDTO;
import com.apnafarmers.dto.VerifyTokenRequestDTO;
import com.apnafarmers.entity.UserInfo;
import com.apnafarmers.exception.DataNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Description(value = "Service responsible for handling OTP related functionality.")
@Service
@Slf4j
public class OtpService {

	private OtpGenerator otpGenerator;
	private EmailService emailService;
	private UserService userService;

	public OtpService(OtpGenerator otpGenerator, EmailService emailService, UserService userService) {
		this.otpGenerator = otpGenerator;
		this.emailService = emailService;
		this.userService = userService;
	}

	public Boolean generateOtp(AuthRequest authRequest) {
		Optional<UserInfo> userInfo;

		if (authRequest.getPh() != null) {
			userInfo = userService.findByPh(authRequest.getPh());
		} else {
			userInfo = userService.findByUserName(authRequest.getUsername());
		}

		UserInfo user = userInfo.orElseThrow(() -> new DataNotFoundException());

		String email = user.getEmail();
		String ph = user.getMobileNumber();

		Integer otpValue = otpGenerator.generateOTP(ph);
		if (otpValue == -1) {
			log.error("OTP generator is not working...");
			return false;
		}

		log.info("Generated OTP: {}", otpValue);

		List<String> recipients = new ArrayList<>();
		recipients.add(email);

		// generate emailDTO object
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setSubject("Spring Boot OTP Password.");
		emailDTO.setBody("OTP Password: " + otpValue);
		emailDTO.setRecipients(recipients);

		// send generated e-mail
		return emailService.sendSimpleMessage(emailDTO);
	}

	public Boolean validateOTP(VerifyTokenRequestDTO verifyTokenRequest) {
		log.info("Inside validateOTP");
		String ph = verifyTokenRequest.getPh();

		// get OTP from cache
		try {
			Integer cacheOTP = otpGenerator.getOPTByKey(ph);

			log.info("Cached OTP {} ", cacheOTP);

			if (cacheOTP != null && cacheOTP.equals(verifyTokenRequest.getOtp())) {
				otpGenerator.clearOTPFromCache(ph);

				UserInfo userInfo = new UserInfo();
				userInfo.setMobileNumber(ph);
				userInfo.setRoles("ROLE_USER");
				Optional<UserInfo> findByPh = userService.findByPh(ph);
				if (findByPh.isEmpty()) {
					userService.saveUser(userInfo);
				}

				return true;
			}

		} catch (Exception e) {
			log.error("Received Error While Saving Data {} ", e);
		}

		return false;
	}
}
