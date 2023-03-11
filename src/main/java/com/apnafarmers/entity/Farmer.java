package com.apnafarmers.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@Table(name = "farmer")
public class Farmer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String profileImage;

	private String firstName;

	private String lastName;

	private String mobileNumber;

	private String whatsAppNumber;

	private String email;

	private String land;

	private String landUnit;

	@Column(columnDefinition = "integer default 0")
	private long rating;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;

	@OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Media> medias = new HashSet<>();

	public void addMedia(Media media) {
		medias.add(media);
		media.setFarmer(this);
	}

	@OneToMany(mappedBy = "farmer", cascade = CascadeType.DETACH, orphanRemoval = true)
	private Set<Crop> crops = new HashSet<>();

	public void addCrop(Crop crop) {
		crops.add(crop);
		crop.setFarmer(this);
	}
}
