package com.apnafarmers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotNull;
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

	private long farmerId;
	private long cropQuality;
	private long cropTypeId;
	private long cropCategoryId;
	private long cropId;
	private String cropName;
	private long land;
	private long landUnitId;
	private double weight;
	private long weightUnitId;
	private double rate;
	private long rateUnitId;
	private String availabilityDate;
	private String createdDate;
	private boolean addressSameAsProfile;
	private String address;
	private String address2;

	@NotNull
	private long stateId;
	
	@NotNull
	private long districtId;
	
	@NotNull
	private long tehsilId;
	
	@NotNull
	private long cityid;
	
	@NotNull
	private String pinCode;
	
	private String description;
	private List<MediaDTO> media;

}
