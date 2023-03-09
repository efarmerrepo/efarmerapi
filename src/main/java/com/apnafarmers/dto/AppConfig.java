package com.apnafarmers.dto;

import java.util.List;

import com.apnafarmers.entity.AndroidAppConfig;
import com.apnafarmers.entity.BuyerType;
import com.apnafarmers.entity.CropType;
import com.apnafarmers.entity.LandUnit;
import com.apnafarmers.entity.WeightUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(Include.NON_NULL)
public class AppConfig {

	AndroidAppConfig androidAppConfig;

	List<WeightUnit> weightUnit;

	List<LandUnit> landUnit;

	List<BuyerType> buyerType;
	
	List<CropType> categories;

}
