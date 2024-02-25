package com.nodji.awards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nodji.awards.dto.YearWinnerMovieDTO;
import com.nodji.awards.model.Movie;
import com.nodji.awards.util.Constants;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByYearm(Integer year);

	@Query(value = Constants.SQL_FIND_YEAR_MODE_ONE_WINNER)
	List<YearWinnerMovieDTO> findYearsWithModeThanOneWinner();

}
