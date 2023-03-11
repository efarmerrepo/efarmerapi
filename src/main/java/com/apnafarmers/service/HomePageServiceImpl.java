package com.apnafarmers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.HomePageResponse;
import com.apnafarmers.entity.Banner;
import com.apnafarmers.repository.BannerRepository;

@Service
public class HomePageServiceImpl implements HomePageService{

	@Autowired
	BannerRepository bannerRepository;
	
	@Override
	public HomePageResponse getHomePageResponse() {
		 List<Banner> findAll = bannerRepository.findAll();
		 
		 return HomePageResponse.builder()
				 .banners(findAll)
				 .build();
	}

}
