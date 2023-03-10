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
	public List<Crop> getCropByParemeters(Map<String, String> querryParam) {

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
			String state = locationService.findStateById(Long.valueOf(stateId));

		} else if (StringUtils.isNotEmpty(districtId)) {

		} else if (StringUtils.isNotEmpty(cityId)) {

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
//			crops = cropRepository.findByPinCode(pinCode);
		} else if (StringUtils.isNotEmpty(avilabilityFromDate)) {

		} else if (StringUtils.isNotEmpty(avilabilityToDate)) {

		} else if (StringUtils.isNotEmpty(avilabilityFromDate) && StringUtils.isNotEmpty(avilabilityToDate)) {

		} else if (StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(offset)) {
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.DESC, "name"));
			Page<Crop> findAll = cropRepository.findAll(pageLimit);
			crops = findAll.getContent();
		} else {
			crops = cropRepository.findAll();
		}

		return crops;
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

		return null;
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
			location.setCity(cityRepository.findById(request.getCityid()).orElse(null));
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

}
