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
public class BuyerDto {

	private String firstName;
	private String lastName;
	private long buyerTypeId;
	private String mobileNumber;
	private String whatsappNumber;
	private String email;
	private String address1;
	private String address2;
	private String stateId;
	private String districtId;
	private String tehsilId;
	private String city;
	private String pinCode;
	private String latitude;
	private String longitude;
	private String companyName;

	private List<CropRequest> crops;

}
