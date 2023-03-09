package com.apnafarmers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonInclude(Include.NON_NULL)
public class FarmerDto {

	private long farmerId;
	private String profileImage;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String whatsappNumber;
	private String email;
	private String latitude;
	private String longitude;
	private String address1;
	private String village;
	private String stateId;
	private String districtId;
	private String tehsilId;
	private String city;
	private String pinCode;
	private String land;
	private String landUnit;
	private List<MediaDto> media;
	private List<CropDto> crops;
}
