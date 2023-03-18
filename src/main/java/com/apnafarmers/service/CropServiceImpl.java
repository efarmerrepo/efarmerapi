package com.apnafarmers.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.CropRequest;
import com.apnafarmers.dto.CropResponse;
import com.apnafarmers.dto.MediaDTO;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.CropCategory;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.entity.Location;
import com.apnafarmers.entity.Media;
import com.apnafarmers.exception.DataNotFoundException;
import com.apnafarmers.repository.CityRepository;
import com.apnafarmers.repository.CropCategoryRepository;
import com.apnafarmers.repository.CropQualityRepository;
import com.apnafarmers.repository.CropRepository;
import com.apnafarmers.repository.CropTypeRepository;
import com.apnafarmers.repository.DistrictRepository;
import com.apnafarmers.repository.FarmerRepository;
import com.apnafarmers.repository.LandUnitRepository;
import com.apnafarmers.repository.LocationRepository;
import com.apnafarmers.repository.RateUnitRepository;
import com.apnafarmers.repository.StateRepository;
import com.apnafarmers.repository.TehsilRepository;
import com.apnafarmers.repository.WeightUnitRepository;
import com.apnafarmers.utils.ApnaFarmersConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CropServiceImpl implements CropService {

	@Autowired
	CropRepository cropRepository;

	@Autowired
	FarmerRepository farmerRepository;

	@Autowired
	LocationService locationService;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	LocationRepository locationRepository;

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
	LandUnitRepository landUnitRepository;

	@Autowired
	WeightUnitRepository weightUnitRepository;

	@Autowired
	CropTypeRepository cropTypeRepository;

	@Autowired
	CropQualityRepository cropQualityRepository;

	@Override
	public Crop saveCrop(Crop crop, Long farmerId) {
		Optional<Farmer> findById = farmerRepository.findById(farmerId);
		crop.setFarmer(findById.orElse(null));
		log.info("Saving the crop {}", crop);
		return cropRepository.save(crop);

	}

	@Override
	public List<CropCategory> getCropCategories() {
		return cropCategoryRepository.findAll();
	}

	@Override
	public CropCategory saveCropCategory(CropCategory cropType) {
		CropCategory save = cropCategoryRepository.save(cropType);
		return save;
	}

	@Override
	public Set<Crop> getCropNameByCategory(Long cropCategory) {

		Optional<CropCategory> findById = cropCategoryRepository.findById(cropCategory);
		CropCategory cropType = findById.orElseThrow(() -> new DataNotFoundException());
		Set<Crop> crops = cropType.getCrops();
		return crops;
	}

	@Override
	public List<CropResponse> getCropByParemeters(Map<String, String> querryParam) {

		String stateId = querryParam.get(ApnaFarmersConstants.STATE_ID);
		String districtId = querryParam.get(ApnaFarmersConstants.DISTRICT_ID);
		String cityId = querryParam.get(ApnaFarmersConstants.CITY_ID);
		String cropCategory = querryParam.get(ApnaFarmersConstants.CROP_CATEGORY);
		String cropId = querryParam.get(ApnaFarmersConstants.CROP_ID);
		String quality = querryParam.get(ApnaFarmersConstants.QUALITY);
		String cropType = querryParam.get(ApnaFarmersConstants.CROP_TYPE);
		String pinCode = querryParam.get(ApnaFarmersConstants.PINCODE);
		String avilabilityFromDate = querryParam.get(ApnaFarmersConstants.AVAILABILITY_FROM_DATE);
		String avilabilityToDate = querryParam.get(ApnaFarmersConstants.AVAILABILITY_TO_DATE);
		String limit = querryParam.get(ApnaFarmersConstants.LIMIT);
		String offset = querryParam.get(ApnaFarmersConstants.OFFSET);

		List<Crop> crops = null;

		if (StringUtils.isNotEmpty(stateId)) {

			crops = cropRepository.findByStateId(Long.valueOf(stateId));

		} else if (StringUtils.isNotEmpty(districtId)) {
			crops = cropRepository.findByDistrictId(Long.valueOf(districtId));

		} else if (StringUtils.isNotEmpty(cityId)) {
			crops = cropRepository.findByCityId(Long.valueOf(cityId));

		} else if (StringUtils.isNotEmpty(cropCategory)) {
			Optional<CropCategory> findById = cropCategoryRepository.findById(Long.valueOf(cropCategory));
			CropCategory cropTypeFromDb = findById.orElseThrow(() -> new DataNotFoundException());
			Set<Crop> crops2 = cropTypeFromDb.getCrops();
			crops = new ArrayList<>(crops2);

		} else if (StringUtils.isNotEmpty(cropId)) {
			Optional<Crop> findById = cropRepository.findById(Long.valueOf(cropId));
			Crop crop = findById.orElseThrow(null);
			crops = new ArrayList<>();
			crops.add(crop);
		} else if (StringUtils.isNotEmpty(quality)) {

		} else if (StringUtils.isNotEmpty(cropType)) {
			Optional<CropCategory> findByName = cropCategoryRepository.findByName(cropType);
			CropCategory orElseThrow = findByName.orElseThrow();
			Set<Crop> crops2 = orElseThrow.getCrops();
			crops = new ArrayList<>(crops2);
		} else if (StringUtils.isNotEmpty(pinCode)) {
			crops = cropRepository.findByPinCode(Long.valueOf(pinCode));
		} else if (StringUtils.isNotEmpty(avilabilityFromDate)) {

			DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
					.toFormatter(Locale.ENGLISH);

			LocalDate date = LocalDate.parse(avilabilityFromDate, df);
			crops = cropRepository.findByAvilabilityFromDate(date);

		} else if (StringUtils.isNotEmpty(avilabilityToDate)) {

			DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
					.toFormatter(Locale.ENGLISH);

			LocalDate date = LocalDate.parse(avilabilityToDate, df);
			crops = cropRepository.findByAvilabilityToDate(date);

		} else if (StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(offset)) {
			Integer o = Integer.valueOf(offset);
			Integer l = Integer.valueOf(limit);
			Pageable pageLimit = PageRequest.of(o * l, o * l - 1, Sort.by(Sort.Direction.DESC, "name"));
			Page<Crop> findAll = cropRepository.findAll(pageLimit);
			crops = findAll.getContent();
		} else {
			Pageable pageLimit = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "name"));
			Page<Crop> findAll = cropRepository.findAll(pageLimit);
			crops = findAll.getContent();
		}

		List<CropResponse> mapCroptoCropRequest = mapCroptoCropRequest(crops);

		return mapCroptoCropRequest;
	}

	private List<CropResponse> mapCroptoCropRequest(List<Crop> crops) {

		List<CropResponse> cropResponseList = new ArrayList<>();

		for (Crop crop : crops) {
			CropResponse cropResponse = new CropResponse();
			cropResponse.setId(crop.getId());

			Farmer farmer = crop.getFarmer();
			if (farmer != null) {
				cropResponse.setFirstName(farmer.getFirstName());
				cropResponse.setLastName(farmer.getLastName());
			}

			if (crop.getCropType() != null) {
				cropResponse.setCropTypeid(crop.getCropType().getId());
				cropResponse.setCropType(crop.getCropType().getName());
			}

			if (crop.getCropCategory() != null) {
				cropResponse.setCropCategoryId(crop.getCropCategory().getId());
				cropResponse.setCropCategory(crop.getCropCategory().getName());
			}

			cropResponse.setCropName(crop.getName());
			cropResponse.setRate(crop.getRate());
			cropResponse.setQuantity(crop.getWeight());
			if (crop.getWeightUnit() != null) {
				cropResponse.setQuantityUnit(crop.getWeightUnit().getName());
			}
			cropResponse.setLand(crop.getLand());

			if (crop.getLandUnit() != null) {
				cropResponse.setLandUnit(crop.getLandUnit().getName());
			}

			log.info("crop.getLocation {}", crop.getLocation());

			if (crop.getLocation().getCity() != null) {
				cropResponse.setCity(crop.getLocation().getCity().getName());
			}

			if (crop.getLocation().getDistrict() != null) {
				cropResponse.setDistrict(crop.getLocation().getDistrict().getName());
			}

			cropResponse.setPinCode(crop.getLocation().getPinCode());

			List<MediaDTO> mediaResponse = new ArrayList<>();
			Set<Media> medList = crop.getMedias();
			if (medList != null) {
				for (Media media : medList) {
					MediaDTO mediaDto = new MediaDTO();
					mediaDto.setType(media.getUrl());
					mediaDto.setUrl(media.getType());
					mediaResponse.add(mediaDto);
				}
				cropResponse.setMedia(mediaResponse);
				cropResponseList.add(cropResponse);
			}
		}

		return cropResponseList;
	}

	@Override
	public Crop saveCrop(CropRequest request) {
		Crop crop = new Crop();
		mapCropRequestToCropEntity(request, crop);
		cropRepository.save(crop);
		return crop;
	}

	@Override
	public Crop updateCrop(CropRequest request) {

		Crop crop;
		if (request.getCropId() != null) {
			crop = cropRepository.findById(request.getFarmerId()).orElseThrow(
					() -> new DataNotFoundException("Crop is not Found with given Id " + request.getCropId()));
		} else {
			throw new DataNotFoundException("CropId is mandatory for update ");
		}

		if (StringUtils.isNotEmpty(request.getCropName())) {
			crop.setName(request.getCropName());
		}

		if (request.getFarmerId() != null) {
			crop.setFarmer(farmerRepository.findById(request.getFarmerId()).orElse(null));
		}

		if (request.getCropQuality() != null) {
			crop.setCropQuality(cropQualityRepository.findById(request.getCropQuality()).orElse(null));
		}

		if (request.getCropCategoryId() != null) {
			crop.setCropCategory(cropCategoryRepository.findById(request.getCropCategoryId()).orElse(null));
		}

		if (request.getCropTypeId() != null) {
			crop.setCropType(cropTypeRepository.findById(request.getCropTypeId()).orElse(null));
		}

		if (request.getLand() != null) {
			crop.setLand(request.getLand());
		}

		if (request.getLandUnitId() != null) {
			crop.setLandUnit(landUnitRepository.findById(request.getLandUnitId()).orElse(null));
		}

		if (request.getWeight() != null) {
			crop.setWeight(request.getWeight());
		}

		if (request.getWeightUnitId() != null) {
			crop.setWeightUnit(weightUnitRepository.findById(request.getWeightUnitId()).orElse(null));
		}

		if (request.getRate() != null) {
			crop.setRate(request.getRate());
		}

		if (request.getRateUnitId() != null) {
			crop.setRateUnit(rateUnitRepository.findById(request.getRateUnitId()).orElse(null));
		}

		Location location = crop.getLocation();
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

		if (request.getTehsilId() != null) {
			location.setTehsil(tehsilRepository.findById(request.getTehsilId()).orElse(null));
		}

		if (request.getCityId() != null) {
			location.setCity(cityRepository.findById(request.getCityId()).orElse(null));
		}

		if (StringUtils.isNotEmpty(request.getPinCode())) {
			location.setPinCode(request.getPinCode());
		}

		crop.setLocation(location);

		if (StringUtils.isNotEmpty(request.getDescription())) {
			crop.setDescription(request.getDescription());
		}

		DateTimeFormatter df = new DateTimeFormatterBuilder()
//				.parseCaseInsensitive()
				.appendPattern("dd-MM-yyyy").toFormatter(Locale.ENGLISH);

		if (StringUtils.isNotEmpty(request.getAvailabilityDate())) {
			crop.setAvailabilityDate(LocalDate.parse((request.getAvailabilityDate()), df));
		} else {
			LocalDate currentDate = LocalDate.now();
			crop.setCreatedDate(currentDate);
		}

		log.info("updating Crop {} ", crop);
		Crop save = cropRepository.save(crop);

		return save;
	}

	@Override
	public void deleteCropCategories(long cropCategoryId) {

		cropCategoryRepository.deleteById(cropCategoryId);
	}

	private void mapCropRequestToCropEntity(CropRequest request, Crop crop) {
		long farmerId = request.getFarmerId();
		Optional<Farmer> findById = farmerRepository.findById(farmerId);
		Farmer farmer = findById.orElse(null);
		crop.setFarmer(farmer);

		crop.setCropQuality(cropQualityRepository.findById(request.getCropQuality()).orElse(null));
		crop.setCropType(cropTypeRepository.findById(request.getCropTypeId()).orElse(null));
		crop.setCropCategory(cropCategoryRepository.findById(request.getCropCategoryId()).orElse(null));
		crop.setName(request.getCropName());
		crop.setLand(request.getLand());

		crop.setLandUnit(landUnitRepository.findById(request.getLandUnitId()).orElse(null));
		crop.setWeight(request.getWeight());
		crop.setWeightUnit(weightUnitRepository.findById(request.getWeightUnitId()).orElse(null));
		crop.setRate(request.getRate());
		crop.setRateUnit(rateUnitRepository.findById(request.getRateUnitId()).orElse(null));

		DateTimeFormatter df = new DateTimeFormatterBuilder()
//				.parseCaseInsensitive()
				.appendPattern("dd-MM-yyyy").toFormatter(Locale.ENGLISH);

		if (StringUtils.isNotEmpty(request.getCreatedDate())) {
			crop.setCreatedDate(LocalDate.parse((request.getCreatedDate()), df));
		} else {
			LocalDate currentDate = LocalDate.now();
			crop.setCreatedDate(currentDate);
		}

		if (StringUtils.isNotEmpty(request.getAvailabilityDate())) {
			crop.setAvailabilityDate(LocalDate.parse((request.getAvailabilityDate()), df));
		} else {
			crop.setAvailabilityDate(null);
		}

		Location location = null;
		if (request.isAddressSameAsProfile()) {
			location = farmer.getLocation();
		} else {
			location = new Location();
			location.setAddress1(request.getAddress());
			location.setAddress2(request.getAddress2());
			location.setState(stateRepository.findById(request.getStateId()).orElse(null));
			location.setDistrict(districtRepository.findById(request.getDistrictId()).orElse(null));
			location.setTehsil(tehsilRepository.findById(request.getTehsilId()).orElse(null));
			location.setCity(cityRepository.findById(request.getCityId()).orElse(null));
			location.setPinCode(request.getPinCode());
		}
		crop.setLocation(location);

		List<MediaDTO> mediaDtoList = request.getMedia();
		if (mediaDtoList != null) {
			for (MediaDTO mediaDto : mediaDtoList) {
				Media media = new Media();
				media.setType(mediaDto.getType());
				media.setUrl(mediaDto.getUrl());
				crop.addMedia(media);
			}
		}
		crop.setDescription(request.getDescription());
	}

	@Override
	public void deleteCropById(Long cropId) {
		cropRepository.deleteById(cropId);
	}

}
