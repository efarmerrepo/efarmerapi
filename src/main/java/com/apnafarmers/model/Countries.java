package com.apnafarmers.model;

import java.util.List;

import com.apnafarmers.entity.Country;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Countries {

	private List<Country> countries;

}
