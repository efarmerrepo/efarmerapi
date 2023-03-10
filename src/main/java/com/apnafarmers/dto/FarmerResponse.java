package com.apnafarmers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class FarmerResponse {
	
	private long farmerId;
	private String profileImage;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String whatsappNumber;
	private String email;
	
	private String address;
	private String address2;
	private String latitude;
	private String longitude;
	private String village;
	private long stateId;
	private long districtId;
	private long tehsilId;
	private long cityId;
	private String pinCode;
	
	private String land;
	private String landUnit;
	private List<MediaDTO> media;
	private List<CropResponse> crops;

}
