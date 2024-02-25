package com.nodji.awards.service;

import java.io.Reader;
import java.util.List;

import com.nodji.awards.dto.MovieDTO;
import com.nodji.awards.dto.YearWinnerDTO;
import com.nodji.awards.exception.BadRequestException;
import com.nodji.awards.model.Movie;

public interface MovieService {

	List<Movie> getMoviesFromAYear(Integer year);

	List<MovieDTO> getMoviesByYear(Integer year);

	YearWinnerDTO getYearsWithMoreThanOneWinners();

	void remove(Long id) throws BadRequestException;

	void chargeDate(Reader reader);
}
