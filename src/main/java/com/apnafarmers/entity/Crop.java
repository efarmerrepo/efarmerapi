package com.apnafarmers.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crop")
@JsonInclude(Include.NON_EMPTY)
public class Crop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private Double rate;

	@JsonIgnore
	@ManyToOne( cascade = CascadeType.DETACH)
	private RateUnit rateUnit;

	private Double quantity;

	private String quantityUnit;

	private long land;

	private boolean addressSameAsProfile;

	private String description;

	private LocalDate availabilityDate;

	private LocalDate createdDate;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonIgnore
	private LandUnit landUnit;

	@OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Media> medias = new HashSet<>();

	public void addMedia(Media media) {
		medias.add(media);
		media.setCrop(this);
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.DETACH)
	private Farmer farmer;

	@JsonIgnore
	@ManyToOne( cascade = CascadeType.DETACH)
	private CropCategory cropCategory;

	@JsonIgnore
	@ManyToOne( cascade = CascadeType.DETACH)
	private CropType cropType;

	private double weight;

	@JsonIgnore
	@ManyToOne( cascade = CascadeType.DETACH)
	private WeightUnit weightUnit;

	@JsonIgnore
	@ManyToOne( cascade = CascadeType.DETACH)
	private CropQuality cropQuality;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.DETACH)
	private Buyer buyer;

}
