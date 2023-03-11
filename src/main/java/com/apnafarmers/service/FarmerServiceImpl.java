package com.apnafarmers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.AppConfig;
import com.apnafarmers.dto.CropResponse;
import com.apnafarmers.dto.FarmerRequest;
import com.apnafarmers.dto.FarmerResponse;
import com.apnafarmers.dto.MediaDTO;
import com.apnafarmers.entity.AndroidAppConfig;
import com.apnafarmers.entity.BuyerType;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropCategory;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.entity.LandUnit;
import com.apnafarmers.entity.Location;
import com.apnafarmers.entity.Media;
import com.apnafarmers.entity.WeightUnit;
import com.apnafarmers.exception.DataNotFoundException;
import com.apnafarmers.exception.ResourceNotFoundException;
import com.apnafarmers.repository.AndroidAppConfigRepository;
import com.apnafarmers.repository.BuyerTypeRepository;
import com.apnafarmers.repository.CityRepository;
import com.apnafarmers.repository.CropCategoryRepository;
import com.apnafarmers.repository.CropQualityRepository;
import com.apnafarmers.repository.DistrictRepository;
import com.apnafarmers.repository.FarmerRepository;
import com.apnafarmers.repository.LandUnitRepository;
import com.apnafarmers.repository.RateUnitRepository;
import com.apnafarmers.repository.StateRepository;
import com.apnafarmers.repository.TehsilRepository;
import com.apnafarmers.repository.WeightUnitRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	private BuyerTypeRepository buyerTypeRepository;

	@Autowired
	private AndroidAppConfigRepository androidAppConfigRepository;

	@Autowired
	private LandUnitRepository landUnitRepository;

	@Autowired
	private WeightUnitRepository weightUnitRepository;

	@Autowired
	private CropCategoryRepository cropTypeRepository;

	@Autowired
	FarmerRepository farmerRepository;

	@Autowired
	LocationService locationService;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	DistrictRepository districtRepository;

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
	public AppConfig getAndroidAppConfig() {
		// @formatter:off
		List<AndroidAppConfig> androidAppConfigList = androidAppConfigRepository.findAll();
		List<BuyerType> buyerTypeList = buyerTypeRepository.findAll();
		List<LandUnit> landUnitList = landUnitRepository.findAll();
		List<WeightUnit> weightUnitList = weightUnitRepository.findAll();
		List<CropCategory> cropTypeList = cropTypeRepository.findAll();
		
		List<CropCategory> removeCropFromCropType = new ArrayList<>();
		for (CropCategory cropType : cropTypeList) {
			cropType.setCrops(null);
			removeCropFromCropType.add(cropType);
		}
		
		AndroidAppConfig androidAppConfig = null;
		if(androidAppConfigList.size() > 0) {
			 androidAppConfig = androidAppConfigList.get(0);
		}
		
		AppConfig build = AppConfig.builder()
		.androidAppConfig(androidAppConfig)
		.buyerType(buyerTypeList)
		.landUnit(landUnitList)
		.weightUnit(weightUnitList)
		.categories(removeCropFromCropType)
		.build();
		return build;
	}

	@Override
	public Farmer saveFarmer(FarmerRequest request) {
		
		Farmer farmer = new Farmer();

		farmer.setProfileImage(request.getProfileImage());
		farmer.setFirstName(request.getFirstName());
		farmer.setLastName(request.getLastName());
		farmer.setMobileNumber(request.getMobileNumber());
		farmer.setWhatsAppNumber(request.getWhatsappNumber());
		farmer.setEmail(request.getEmail());

		
		Location location = new Location();
		location.setLatitude(request.getLatitude());
		location.setLongitude(request.getLongitude());
		location.setAddress1(request.getAddress());
		location.setAddress2(request.getAddress2());
		location.setState(stateRepository.findById(request.getStateId()).orElse(null));
		location.setDistrict(districtRepository.findById(request.getDistrictId()).orElse(null));
		location.setTehsil(tehsilRepository.findById(request.getTehsilId()).orElse(null));
		location.setCity(cityRepository.findById(request.getCityId()).orElse(null));
		location.setPinCode(request.getPinCode());
		farmer.setLocation(location);

		farmer.setLand(request.getLand());
		farmer.setLandUnit(request.getLandUnit());
		
		List<MediaDTO> mediaDtoList = request.getMedia();
		if (mediaDtoList != null) {
			for (MediaDTO mediaDto : mediaDtoList) {
				Media media = new Media();
				media.setType(mediaDto.getType());
				media.setUrl(mediaDto.getUrl());
				farmer.addMedia(media);
			}
		}
		
		log.info("Saving Farmer {} ", farmer);

		Farmer save = farmerRepository.save(farmer);
		return save;
	}

	@Override
	public FarmerResponse findById(long farmerId) {
		
		FarmerResponse farmerResponse = new FarmerResponse();
		
		Farmer farmer = farmerRepository.findById(farmerId).orElseThrow(() -> new ResourceNotFoundException(""));
		
		farmerResponse.setFarmerId(farmerId);
		farmerResponse.setProfileImage(farmer.getProfileImage());
		farmerResponse.setFirstName(farmer.getFirstName());
		farmerResponse.setLastName(farmer.getLastName());
		farmerResponse.setMobileNumber(farmer.getMobileNumber());
		farmerResponse.setWhatsappNumber(farmer.getWhatsAppNumber());
		farmerResponse.setEmail(farmer.getEmail());
		
		List<CropResponse> cropResponseList = new ArrayList<>();
		Set<Crop> crops = farmer.getCrops();
		if(crops != null) {
			for (Crop crop : crops) {
				CropResponse cropResponse = new CropResponse();
				cropResponse.setId(crop.getId());
				if(crop.getCropCategory() != null) {
					cropResponse.setCropCategoryId(crop.getCropCategory().getId());
					cropResponse.setCropCategory(crop.getCropCategory().getName());
				}
				
				cropResponse.setCropName(crop.getName());
				cropResponse.setRate(crop.getRate());
				cropResponse.setQuantity(crop.getQuantity());
				cropResponse.setQuantityUnit(crop.getQuantityUnit());
				cropResponse.setLand(crop.getLand());
				if(crop.getLandUnit() != null){
					cropResponse.setLandUnit(crop.getLandUnit().getName());
				}
				
				cropResponse.setCity(crop.getLocation().getCity().getName());
				cropResponse.setDistrict(crop.getLocation().getDistrict().getName());
				cropResponse.setPinCode(crop.getLocation().getPinCode());
				
				Set<Media> mediaList = crop.getMedias();
				List<MediaDTO> cropMediaDtoList = new ArrayList<>();
				if(mediaList != null) {
					for (Media media : mediaList) {
						MediaDTO cropMediaDto = new MediaDTO();
						cropMediaDto.setType(media.getType());
						cropMediaDto.setUrl(media.getUrl());
						cropMediaDtoList.add(cropMediaDto);
					}
					cropResponse.setMedia(cropMediaDtoList);
				}
				cropResponseList.add(cropResponse);
			}
		}
		
		farmerResponse.setCrops(cropResponseList);
		
		Set<Media> mediaList = farmer.getMedias();
		List<MediaDTO> mediaDtoList = new ArrayList<>();
		if(mediaList!= null) {
			for (Media media : mediaList) {
				MediaDTO mediaDTO = new MediaDTO(); 
				mediaDTO.setType(media.getType());
				mediaDTO.setUrl(media.getUrl());;
				mediaDtoList.add(mediaDTO);
			}
			farmerResponse.setMedia(mediaDtoList);
		}
				
		return farmerResponse;
	}

	@Override
	public Farmer updateFarmer(FarmerRequest request) {
		
		Farmer farmer;
		if (request.getFarmerId() != null) {
			farmer = farmerRepository.findById(request.getFarmerId()).orElseThrow(
					() -> new DataNotFoundException("Farmer is not Found with given Id " + request.getFarmerId()));
		} else {
			throw new DataNotFoundException("FarmerId is mandatory for update ");
		}

		if (StringUtils.isNotEmpty(request.getProfileImage())) {
			farmer.setProfileImage(request.getProfileImage());
		}
		
		if (StringUtils.isNotEmpty(request.getFirstName())) {
			farmer.setFirstName(request.getFirstName());
		}
		if (StringUtils.isNotEmpty(request.getLastName())) {
			farmer.setLastName(request.getLastName());
		}
		if (StringUtils.isNotEmpty(request.getMobileNumber())) {
			farmer.setMobileNumber(request.getMobileNumber());
		}
		if (StringUtils.isNotEmpty(request.getWhatsappNumber())) {
			farmer.setWhatsAppNumber(request.getWhatsappNumber());
		}
		if (StringUtils.isNotEmpty(request.getEmail())) {
			farmer.setEmail(request.getEmail());
		}
		
		Location location = farmer.getLocation();
		if (StringUtils.isNotEmpty(request.getLatitude())) {
			location.setLatitude(request.getLatitude());
		}
		
		if (StringUtils.isNotEmpty(request.getLongitude())) {
			location.setLongitude(request.getLongitude());
		}
		
		if (StringUtils.isNotEmpty(request.getAddress())) {
			location.setAddress1(request.getAddress());
		}
		
		if (StringUtils.isNotEmpty(request.getAddress2())) {
			location.setAddress2(request.getAddress2());
		}
		
		
		if (request.getStateId() != null) {
			location.setState(stateRepository.findById(request.getStateId()).orElse(null));
		}
		
		if (request.getDistrictId() != null) {
			location.setDistrict(districtRepository.findById(request.getDistrictId()).orElse(null));
		}
		
		if (request.getTehsilId()!= null) {
			location.setTehsil(tehsilRepository.findById(request.getTehsilId()).orElse(null));
		}

		if (request.getCityId()!= null) {
			location.setCity(cityRepository.findById(request.getCityId()).orElse(null));
		}

		if (StringUtils.isNotEmpty(request.getPinCode())) {
			location.setPinCode(request.getPinCode());
		}
		
		farmer.setLocation(location);

		if (StringUtils.isNotEmpty(request.getPinCode())) {
			farmer.setLand(request.getLand());
		}
		
		if (StringUtils.isNotEmpty(request.getLandUnit())) {
			farmer.setLandUnit(request.getLandUnit());
		}
		

		log.info("updating Farmer {} ", farmer);
		
		//TODO: Update media remaining
		

		Farmer save = farmerRepository.save(farmer);
		return save;
	}

}
