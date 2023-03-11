package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.Crop;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

	List<Crop> findByNameStartsWithIgnoreCase(String startWith);

	List<Crop> findByNameStartsWithIgnoreCaseOrderByName(String rating, Pageable pg);

	Crop findByNameIgnoreCase(String name);

//	@Query("SELECT c FROM Crop c where c.location.pincode in :pincode")
//	List<Crop> findCropByLocationId(@Param("locationList") List<Long> locationList);

	@Query("SELECT c FROM Crop c where c.location.state.id in :stateId")
	List<Crop> findByStateId(@Param("stateId") Long stateId);

	@Query("SELECT c FROM Crop c where c.location.district.id in :districtId")
	List<Crop> findByDistrict(@Param("districtId") Long districtId);
	
	@Query("SELECT c FROM Crop c where c.location.city.id in :cityId")
	List<Crop> findByCity(@Param("cityId") Long cityId);
//	
//	List<Crop> findByPinCode(String pincode);

}
