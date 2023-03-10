package com.apnafarmers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.BuyerCropDto;
import com.apnafarmers.dto.BuyerRequest;
import com.apnafarmers.dto.MediaDTO;
import com.apnafarmers.entity.Buyer;
import com.apnafarmers.entity.BuyerCrop;
import com.apnafarmers.entity.Location;
import com.apnafarmers.entity.Media;
import com.apnafarmers.repository.BuyerRepository;
import com.apnafarmers.repository.BuyerTypeRepository;
import com.apnafarmers.repository.CityRepository;
import com.apnafarmers.repository.CropCategoryRepository;
import com.apnafarmers.repository.CropQualityRepository;
import com.apnafarmers.repository.DistrictRepository;
import com.apnafarmers.repository.RateUnitRepository;
import com.apnafarmers.repository.StateRepository;
import com.apnafarmers.repository.TehsilRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private BuyerTypeRepository buyerTypeRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	TehsilRepository tehsilRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	RateUnitRepository rateUnitRepository;

	@Autowired
	CropCategoryRepository cropCategoryRepository;

	@Autowired
	CropQualityRepository cropQualityRepository;

	@Override
	public Buyer saveBuyer(BuyerRequest request) {

		Buyer buyer = new Buyer();

		buyer.setProfileImage(request.getProfileImage());
		buyer.setFirstName(request.getFirstName());
		buyer.setLastName(request.getLastName());
		buyer.setMobileNumber(request.getMobileNumber());
		buyer.setWhatsappNumber(request.getWhatsappNumber());
		buyer.setEmail(request.getEmail());
		buyer.setCompanyName(request.getCompanyName());

		buyer.setBuyerType(buyerTypeRepository.findById(request.getBuyerTypeId()).orElse(null));

		Location location = new Location();
		location.setLatitude(request.getLatitude());
		location.setLongitude(request.getLongitude());
		location.setAddress1(request.getAddress1());
		location.setAddress2(request.getAddress2());
		location.setState(stateRepository.findById(request.getStateId()).orElse(null));
		location.setDistrict(districtRepository.findById(request.getDistrictId()).orElse(null));
		location.setTehsil(tehsilRepository.findById(request.getTehsilId()).orElse(null));
		location.setCity(cityRepository.findById(request.getCityId()).orElse(null));
		location.setPinCode(request.getPinCode());
		buyer.setLocation(location);

		List<MediaDTO> mediaDtoList = request.getMedia();
		if (mediaDtoList != null) {
			for (MediaDTO mediaDto : mediaDtoList) {
				Media media = new Media();
				media.setType(mediaDto.getType());
				media.setUrl(mediaDto.getUrl());
				buyer.addMedia(media);
			}
		}

		log.info("Saving Buyer {} ", buyer);

		List<BuyerCropDto> buyerCropDtoList = request.getCrops();
		if (buyerCropDtoList != null) {
			for (BuyerCropDto buyerCropDto : buyerCropDtoList) {
				BuyerCrop crop = new BuyerCrop();
				crop.setCropName(buyerCropDto.getCropName());
				buyer.addCrop(crop);
			}
		}

		Buyer save = buyerRepository.save(buyer);
		return save;

	}

}
