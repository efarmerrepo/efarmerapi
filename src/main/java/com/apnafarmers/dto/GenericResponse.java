package com.apnafarmers.dto;

import java.util.List;

import com.apnafarmers.entity.City;
import com.apnafarmers.entity.Country;
import com.apnafarmers.entity.CropType;
import com.apnafarmers.entity.District;
import com.apnafarmers.entity.State;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_EMPTY)
public class GenericResponse {

	private String status;
	private String message;
	private List<Country> countries;
	private List<State> states;
	private List<District> districts;
	private List<City> cities;
	private List<CropType> categories;
	private List<CropDto> crops;

	private String jwtToken;

	private Long farmerId;

	private Long cropId;

	private Long buyerId;

}
