package com.apnafarmers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.CartRequest;
import com.apnafarmers.dto.CropResponse;
import com.apnafarmers.dto.MediaDTO;
import com.apnafarmers.entity.Cart;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.entity.Media;
import com.apnafarmers.exception.DataNotFoundException;
import com.apnafarmers.repository.CartRepository;
import com.apnafarmers.repository.CropRepository;
import com.apnafarmers.repository.UserInfoRepository;
import com.apnafarmers.utils.ApnaFarmersConstants;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	CropRepository cropRepository;

	@Override
	public Cart saveCropInCart(CartRequest request) {
		return null;
	}

	@Override
	public Cart addCropInCart(CartRequest request) {

		Long userId = request.getUserId();
		log.info("user Id : {}", userId);
		userInfoRepository.findById(userId)
				.orElseThrow(() -> new DataNotFoundException("User does'nt exist with the Id " + request.getUserId()));

		Long cropId = request.getCropId();
		log.info("Crop Id : {}", cropId);
		cropRepository.findById(cropId)
				.orElseThrow(() -> new DataNotFoundException("Crop Doesn't Exist with the id " + request.getCropId()));

		Cart cart = new Cart();
		cart.setCropId(request.getCropId());
		cart.setUserId(request.getUserId());
		Cart save = cartRepository.save(cart);
		return save;
	}

	@Override
	public List<CropResponse> getCartCropForUser(long userId) {

		List<Cart> cartList = cartRepository.findByUserId(Long.valueOf(userId))
				.orElseThrow(() -> new DataNotFoundException("User Doesn't Exist with the id " + userId));

		List<Long> cropIdList = new ArrayList<>();

		cartList.stream().forEach((r) -> cropIdList.add(r.getCropId()));

		List<Crop> cropList = cropRepository.findAllById(cropIdList);

		List<CropResponse> mapCroptoCropRequest = mapCroptoCropRequest(cropList);

		return mapCroptoCropRequest;

	}

	@Override
	public Cart updateCropInCart(CartRequest request) {

		Cart cart = new Cart();
		cart.setCropId(request.getCropId());
		cart.setUserId(request.getUserId());
		Cart save = cartRepository.save(cart);
		return save;

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
	public void deleteCart(Map<String, Long> querryParam) {
		Long userId = querryParam.get(ApnaFarmersConstants.USER_ID);
		Long cartId = querryParam.get(ApnaFarmersConstants.CART_ID);
		Long cropId = querryParam.get(ApnaFarmersConstants.CROP_ID);

		if (userId != null) {
			deleteUserFromCart(userId);
		} else if (cropId != null) {
			deleteCropFromCart(cropId);
		} else if (cartId != null) {
			cartRepository.deleteById(cartId);
		}

	}

	@Transactional
	public void deleteUserFromCart(long userId) {
		cartRepository.deleteByUserId(userId);
	}

	@Transactional
	public void deleteCropFromCart(long cropId) {
		cartRepository.deleteByCropId(cropId);

	}

}
