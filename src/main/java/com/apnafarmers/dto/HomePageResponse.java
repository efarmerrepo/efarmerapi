package com.apnafarmers.dto;

import java.util.List;

import com.apnafarmers.entity.Banner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomePageResponse {
  
	List<Banner> banners;

}
