package com.apnafarmers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<List<Cart>> findByUserId(Long valueOf);

	void deleteByCropId(long cropId);

	void deleteByUserId(long userId);

}
