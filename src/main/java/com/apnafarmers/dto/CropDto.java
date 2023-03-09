package com.apnafarmers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class CropDto {

	private Long id;

	private String firstName;
	
	private String lastName;
	
	private Long cropTypeId;

	private String cropType;

	private String cropName;
	
	private String name;

	private Double rate;

	private Long quantity;

	private String quantityUnit;

	private String land;

	private String landUnit;

	private String city;

	private String district;

	private String pinCode;

	private List<MediaDto> media;

}
