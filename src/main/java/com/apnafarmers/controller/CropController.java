package com.apnafarmers.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/farmers")
public class CropController {

//	@Autowired
//	CropService cropService;
//
//	@Operation(summary = "Get All Crops ")
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Successfully fetched the Crop", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)) }),
//			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
//			@ApiResponse(responseCode = "404", description = "Crop not found", content = @Content) })
//	@GetMapping(value = { "/crops" })
//	public ResponseEntity<CropResponse> getAllCrops(@RequestParam(value = "lang", required = false) String language,
//			@RequestParam(value = "st", required = false) String startWith,
//			@RequestParam(value = "sort", required = false) String sort,
//			@RequestParam(value = "limit", required = false) String limit) {
//
//		Map<String, String> querryParam = new HashMap<>();
//		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
//		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
//		querryParam.put(ApnaFarmersConstants.SORT, sort);
//		querryParam.put(ApnaFarmersConstants.LIMIT, limit);
//
//		log.info("Get Crop List");
//
//		List<Crop> cropList = cropService.findAllCrops(querryParam);
//
//		CropResponse cropResponse = CropResponse.builder().build();
//		List<CropModel> cropModelList = new ArrayList<>();
//
//		if (cropList != null && cropList.size() > 0) {
//			for (Crop cr : cropList) {
//				CropType cropType = cr.getCropType();
//				City city = cr.getCity();
//				List<Media> mediaList = cr.getMedia();
//				List<MediaModel> mediaModelList = new ArrayList<>();
//				for (Media media : mediaList) {
//					MediaModel mediaModel = new MediaModel();
//					mediaModel.setType(media.getType());
//					mediaModel.setUrl(media.getUrl());
//					mediaModelList.add(mediaModel);
//				}
//				District district = cr.getDistrict();
//				CropModel cropModel = CropModel.builder().id(cr.getId()).cropTypeId(cropType.getId())
//						.cropType(cropType.getType()).cropName(cr.getName()).rate(cr.getRate())
//						.quantity(cr.getQuantity()).quantityUnit(cr.getQuantityUnit()).land(cr.getLand())
//						.landUnit(cr.getLandUnit()).city(city.getName()).district(district.getName())
//						.pinCode(district.getPinCode()).media(mediaModelList).build();
//				cropModelList.add(cropModel);
//			}
//		}
//
//		cropResponse.setCrops(cropModelList);
//
//		return new ResponseEntity<>(cropResponse, HttpStatus.OK);
//	}
//
//	@Operation(summary = "Get Crop By Id ")
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Successfully fetched the Crop", content = {
//					@Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)) }),
//			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
//			@ApiResponse(responseCode = "404", description = "Crop not found", content = @Content) })
//	@GetMapping(value = { "/crops/{id}" })
//	public ResponseEntity<CropResponse> getCropById(@PathVariable(value = "id", required = false) Long id,
//			@RequestParam(value = "lang", required = false) String language,
//			@RequestParam(value = "st", required = false) String startWith,
//			@RequestParam(value = "sort", required = false) String sort,
//			@RequestParam(value = "limit", required = false) String limit) {
//
//		Map<String, String> querryParam = new HashMap<>();
//		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
//		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
//		querryParam.put(ApnaFarmersConstants.SORT, sort);
//		querryParam.put(ApnaFarmersConstants.LIMIT, limit);
//
//		log.info("Get Crop By id");
//
//		Crop crop = cropService.findCropById(id, querryParam);
//
//		CropResponse cropResponse = CropResponse.builder().build();
//		List<CropModel> cropModelList = new ArrayList<>();
//
//		if (crop != null) {
//			CropType cropType = crop.getCropType();
//			City city = crop.getCity();
//
//			List<Media> mediaList = crop.getMedia();
//			List<MediaModel> mediaModelList = new ArrayList<>();
//			for (Media media : mediaList) {
//				MediaModel mediaModel = new MediaModel();
//				mediaModel.setType(media.getType());
//				mediaModel.setUrl(media.getUrl());
//				mediaModelList.add(mediaModel);
//			}
//
//			District district = crop.getDistrict();
//			CropModel cropModel = CropModel.builder().id(crop.getId()).cropTypeId(cropType.getId())
//					.cropType(cropType.getType()).cropName(crop.getName()).rate(crop.getRate())
//					.quantity(crop.getQuantity()).quantityUnit(crop.getQuantityUnit()).land(crop.getLand())
//					.landUnit(crop.getLandUnit()).city(city.getName()).district(district.getName())
//					.pinCode(district.getPinCode()).media(mediaModelList).build();
//			cropModelList.add(cropModel);
//		}
//
//		cropResponse.setCrops(cropModelList);
//
//		return new ResponseEntity<>(cropResponse, HttpStatus.OK);
//	}

}
