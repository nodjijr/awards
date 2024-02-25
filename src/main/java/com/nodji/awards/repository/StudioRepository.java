package com.nodji.awards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nodji.awards.dto.StudioWinDTO;
import com.nodji.awards.model.Studio;
import com.nodji.awards.util.Constants;

public interface StudioRepository extends JpaRepository<Studio, Long> {

	Studio findByName(String name);

	@Query(value = Constants.SQL_FIND_BY_WINNERS)
	List<StudioWinDTO> findByWinners();

}
