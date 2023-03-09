package com.apnafarmers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	List<State> findByNameStartsWithIgnoreCase(String startWith);

	List<State> findByNameStartsWithIgnoreCaseOrderByName(String rating, Pageable pg);

	Optional<State> findByNameIgnoreCase(String name);

}
