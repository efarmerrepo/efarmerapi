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
public class CropResponse {

	private Long id;

	private String firstName;

	private String lastName;

	private Long cropCategoryId;

	private String cropCategory;

	private Long cropTypeid;

	private String cropType;

	private String cropName;

	private Double rate;

	private Double quantity;

	private String quantityUnit;

	private Long land;

	private String landUnit;

	private String city;

	private String district;

	private String pinCode;

	private List<MediaDTO> media;

}
