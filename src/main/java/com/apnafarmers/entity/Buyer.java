package com.apnafarmers.entity;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Buyer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;

	private String lastName;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.DETACH)
	private BuyerType buyerType;

	private String profileImage;

	private String mobileNumber;

	private String whatsappNumber;

	private String email;

	private String companyName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Crop> crops = new HashSet<>();

	public void addCrop(Crop crop) {
		crops.add(crop);
		crop.setBuyer(this);
	}

	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Media> medias = new HashSet<>();

	public void addMedia(Media media) {
		medias.add(media);
		media.setBuyer(this);
	}

}
