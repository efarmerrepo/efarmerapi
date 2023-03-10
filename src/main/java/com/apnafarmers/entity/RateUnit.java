package com.apnafarmers.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class RateUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

//	@OneToMany(mappedBy = "cropType", cascade = CascadeType.DETACH, orphanRemoval = true)
//	private Set<Crop> crops = new HashSet<>();

//	public void addCrop(Crop crop) {
//		crops.add(crop);
//		crop.setRateUnit(this);
//	}

}
