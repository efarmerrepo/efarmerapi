package com.apnafarmers.model;

import java.util.List;

import com.apnafarmers.entity.District;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Districts {

	private List<District> districts;
}
