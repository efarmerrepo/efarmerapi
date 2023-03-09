package com.apnafarmers.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyTokenRequestDTO {

	@Size(min = 10, max = 10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String mobileNumber;

	private Integer otp;

	private Boolean rememberMe;

}
