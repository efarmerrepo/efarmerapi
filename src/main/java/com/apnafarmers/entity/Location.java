package com.apnafarmers.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String latitude;

	private String longitude;

	private String address1;

	private String address2;

	private String village;

	@OneToOne( cascade = CascadeType.DETACH)
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private State state;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "district_id", referencedColumnName = "id")
	private District district;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "tehsil_id", referencedColumnName = "id")
	private Tehsil tehsil;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private City city;

	private String pinCode;

}
