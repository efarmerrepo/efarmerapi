package com.apnafarmers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.BuyerRequest;
import com.apnafarmers.dto.BuyerResponse;
import com.apnafarmers.dto.CropRequest;
import com.apnafarmers.dto.CropResponse;
import com.apnafarmers.dto.MediaDTO;
import com.apnafarmers.entity.Buyer;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.Location;
import com.apnafarmers.entity.Media;
import com.apnafarmers.exception.DataNotFoundException;
import com.apnafarmers.repository.BuyerRepository;
import com.apnafarmers.repository.BuyerTypeRepository;
import com.apnafarmers.repository.CityRepository;
import com.apnafarmers.repository.CropCategoryRepository;
import com.apnafarmers.repository.CropQualityRepository;
import com.apnafarmers.repository.CropRepository;
import com.apnafarmers.repository.DistrictRepository;
import com.apnafarmers.repository.RateUnitRepository;
import com.apnafarmers.repository.StateRepository;
import com.apnafarmers.repository.TehsilRepository;
import com.apnafarmers.utils.ApnaFarmersConstants;

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
	CropRepository cropRepository;

	@Autowired
	RateUnitRepository rateUnitRepository;

	@Autowired
	CropCategoryRepository cropCategoryRepository;

	@Autowired
	CropQualityRepository cropQualityRepository;

	@Override
	public Buyer saveBuyer(BuyerRequest request) {
		Buyer save = mapBuyerRequestToBuyerEntity(request);
		return save;

	}

	@Override
	public List<BuyerResponse> getBuyer(Map<String, String> querryParam) {

		String cropCategoryId = querryParam.get(ApnaFarmersConstants.CROP_CATEGORY_ID);
		String buyerId = querryParam.get(ApnaFarmersConstants.BUYER_ID);
		String cropId = querryParam.get(ApnaFarmersConstants.CITY_ID);
		List<Buyer> buyerList = new ArrayList<>();

		if (StringUtils.isNotEmpty(cropId)) {
			Crop crop = cropRepository.findById(Long.valueOf(cropId)).orElseThrow(() -> new DataNotFoundException(""));
			Buyer buyer = crop.getBuyer();
			buyerList.add(buyer);

		} else if (StringUtils.isNotEmpty(buyerId)) {
			Buyer buyer = buyerRepository.findById(Long.valueOf(buyerId))
					.orElseThrow(() -> new DataNotFoundException(""));
			buyerList.add(buyer);

		} else if ((StringUtils.isNotEmpty(cropCategoryId))) {

		}

		return mapBuyerToBuyerResponse(buyerList);
	}

	private List<BuyerResponse> mapBuyerToBuyerResponse(List<Buyer> buyerList) {

		List<BuyerResponse> buyerResponseList = new ArrayList<>();

		for (Buyer buyer : buyerList) {
			BuyerResponse buyerResponse = new BuyerResponse();
			buyerResponse.setFirstName(buyer.getFirstName());
			buyerResponse.setLastName(buyer.getLastName());
			buyerResponse.setBuyerTypeId(buyer.getBuyerType().getId());
			buyerResponse.setMobileNumber(buyer.getMobileNumber());
			buyerResponse.setWhatsappNumber(buyer.getWhatsappNumber());
			buyerResponse.setEmail(buyer.getEmail());
			buyerResponse.setAddress1(buyer.getLocation().getAddress1());
			buyerResponse.setAddress2(buyer.getLocation().getAddress2());
			buyerResponse.setStateId(buyer.getLocation().getState().getId());
			buyerResponse.setDistrictId(buyer.getLocation().getDistrict().getId());
			buyerResponse.setTehsilId(buyer.getLocation().getTehsil().getId());
			buyerResponse.setCityId(buyer.getLocation().getCity().getId());
			buyerResponse.setPinCode(buyer.getLocation().getPinCode());
			buyerResponse.setLatitude(buyer.getLocation().getLatitude());
			buyerResponse.setLongitude(buyer.getLocation().getLongitude());
			buyerResponse.setCompanyName(buyer.getCompanyName());

			Set<Crop> cropsList = buyer.getCrops();
			List<CropResponse> cropResponseList = new ArrayList<>();
			if (cropsList != null) {
				for (Crop crop : cropsList) {
					CropResponse cropResponse = new CropResponse();
					cropResponse.setId(crop.getId());
					cropResponse.setCropName(crop.getName());
					cropResponseList.add(cropResponse);
				}
			}
			buyerResponse.setCrops(cropResponseList);

			buyerResponseList.add(buyerResponse);
		}

		return buyerResponseList;
	}

	private Buyer mapBuyerRequestToBuyerEntity(BuyerRequest request) {
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

		List<CropRequest> buyerCropRequestList = request.getCrops();

		if (buyerCropRequestList != null) {
			for (CropRequest buyerCropRequest : buyerCropRequestList) {

				if (cropRepository.findById(buyerCropRequest.getCropId()).isPresent()) {
					buyer.addCrop(cropRepository.findById(buyerCropRequest.getCropId()).orElse(null));
				} else {
				}
			}
		}

		Buyer save = buyerRepository.save(buyer);
		return save;
	}

	@Override
	public Buyer updateBuyer(BuyerRequest request) {

		Buyer buyer;
		if (request.getId() != null) {
			buyer = buyerRepository.findById(request.getId()).orElseThrow(
					() -> new DataNotFoundException("Buyer is not Found with given Id " + request.getId()));
		} else {
			throw new DataNotFoundException("Id is mandatory for update ");
		}

		log.info("Updating the Buyer {}", request);
		log.info("Buyer from DB  {}", buyer);
		if (StringUtils.isNotEmpty(request.getProfileImage())) {
			buyer.setProfileImage(request.getProfileImage());
		}

		if (StringUtils.isNotEmpty(request.getFirstName())) {
			buyer.setFirstName(request.getFirstName());
		}
		if (StringUtils.isNotEmpty(request.getLastName())) {
			buyer.setLastName(request.getLastName());
		}
		if (StringUtils.isNotEmpty(request.getMobileNumber())) {
			buyer.setMobileNumber(request.getMobileNumber());
		}
		if (StringUtils.isNotEmpty(request.getWhatsappNumber())) {
			buyer.setWhatsappNumber(request.getWhatsappNumber());
		}

		if (StringUtils.isNotEmpty(request.getEmail())) {
			buyer.setEmail(request.getEmail());
		}

		if (StringUtils.isNotEmpty(request.getCompanyName())) {
			buyer.setCompanyName(request.getCompanyName());
		}

		if (request.getBuyerTypeId() != null) {
			buyer.setBuyerType(buyerTypeRepository.findById(request.getBuyerTypeId()).orElse(null));
		}

		Location dbLocation = buyer.getLocation();

		if (StringUtils.isNoneEmpty(request.getLatitude())) {
			dbLocation.setLatitude(request.getLatitude());
		}

		if (StringUtils.isNoneEmpty(request.getLongitude())) {
			dbLocation.setLongitude(request.getLongitude());
		}

		if (StringUtils.isNoneEmpty(request.getAddress1())) {
			dbLocation.setAddress1(request.getAddress1());
		}

		if (StringUtils.isNoneEmpty(request.getAddress2())) {
			dbLocation.setAddress2(request.getAddress2());
		}

		if (request.getStateId() != null) {
			dbLocation.setState(stateRepository.findById(request.getStateId()).orElse(null));
		}

		if (request.getDistrictId() != null) {
			dbLocation.setDistrict(districtRepository.findById(request.getDistrictId()).orElse(null));
		}

		if (request.getTehsilId() != null) {
			dbLocation.setTehsil(tehsilRepository.findById(request.getTehsilId()).orElse(null));
		}

		if (request.getCityId() != null) {
			dbLocation.setCity(cityRepository.findById(request.getCityId()).orElse(null));
		}

		if (request.getPinCode() != null) {
			dbLocation.setPinCode(request.getPinCode());
		}

		buyer.setLocation(dbLocation);

		Buyer save = buyerRepository.save(buyer);

		return save;
	}

}
