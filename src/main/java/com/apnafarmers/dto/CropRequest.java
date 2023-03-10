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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class CropRequest {

	private Long farmerId;
	private Long cropQuality;
	private Long cropTypeId;
	private Long cropCategoryId;
	private Long cropId;
	private String cropName;
	private Long land;
	private Long landUnitId;
	private Double weight;
	private Long weightUnitId;
	private Double rate;
	private Long rateUnitId;
	private String availabilityDate;
	private String createdDate;
	private boolean addressSameAsProfile;
	private String address;
	private String address2;

	private Long stateId;

	private Long districtId;

	private Long tehsilId;

	private Long cityid;

	private String pinCode;

	private String description;
	private List<MediaDTO> media;

}
