package com.nodji.awards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nodji.awards.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	Producer findByName(String name);

}
