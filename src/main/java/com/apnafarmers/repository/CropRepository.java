package com.apnafarmers.repository;

import java.time.LocalDate;
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

	@Query("SELECT c FROM Crop c where c.location.state.id in :stateId")
	List<Crop> findByStateId(@Param("stateId") Long stateId);

	@Query("SELECT c FROM Crop c where c.location.district.id in :districtId")
	List<Crop> findByDistrictId(@Param("districtId") Long districtId);

	@Query("SELECT c FROM Crop c where c.location.city.id in :cityId")
	List<Crop> findByCityId(@Param("cityId") Long cityId);

	@Query("SELECT c FROM Crop c where c.location.pinCode in :pinCode")
	List<Crop> findByPinCode(@Param("pinCode") Long pinCode);

	@Query("SELECT c FROM Crop c where c.availabilityDate >=  :date")
	List<Crop> findByAvilabilityFromDate(@Param("date") LocalDate date);

	@Query("SELECT c FROM Crop c where c.availabilityDate <=  :date")
	List<Crop> findByAvilabilityToDate(LocalDate date);

}
