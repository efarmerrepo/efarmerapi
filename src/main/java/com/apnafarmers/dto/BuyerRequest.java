package com.apnafarmers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class BuyerRequest {

	private String firstName;
	private String lastName;
	private String profileImage;
	private Long buyerTypeId;
	private String mobileNumber;
	private String whatsappNumber;
	private String email;
	private String address1;
	private String address2;
	private Long stateId;
	private Long districtId;
	private Long tehsilId;
	private Long cityId;
	private String pinCode;
	private String latitude;
	private String longitude;
	private String companyName;

	private List<BuyerCropDto> crops;

	private String description;
	private List<MediaDTO> media;
}
