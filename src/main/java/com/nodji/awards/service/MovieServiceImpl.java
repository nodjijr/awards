package com.nodji.awards.service;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nodji.awards.dto.MovieDTO;
import com.nodji.awards.dto.YearWinnerDTO;
import com.nodji.awards.dto.YearWinnerMovieDTO;
import com.nodji.awards.exception.BadRequestException;
import com.nodji.awards.exception.NotFoundException;
import com.nodji.awards.model.Movie;
import com.nodji.awards.repository.MovieRepository;
import com.nodji.awards.util.Util;

@Service
public class MovieServiceImpl implements MovieService {

	Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	@Autowired
	private StudioServiceImpl studioService;

	@Autowired
	private ProducerServiceImpl producerService;

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> getMoviesFromAYear(Integer year) {
		return movieRepository.findByYearm(year);
	}

	@Override
	public List<MovieDTO> getMoviesByYear(Integer year) {
		List<Movie> movies = movieRepository.findByYearm(year);
		if (Objects.isNull(movies) || movies.isEmpty()) {
			return new ArrayList<>();
		}
		return movies.stream().map(MovieDTO::new).collect(Collectors.toList());
	}

	@Override
	public YearWinnerDTO getYearsWithMoreThanOneWinners() {
		List<YearWinnerMovieDTO> years = movieRepository.findYearsWithModeThanOneWinner();
		if (Objects.isNull(years) || years.isEmpty()) {
			return new YearWinnerDTO();
		}
		return new YearWinnerDTO(years);
	}

	@Override
	public void remove(Long id) throws BadRequestException {
		Optional<Movie> optional = movieRepository.findById(id);
		if (!optional.isPresent()) {
			throw new NotFoundException();
		}
		Movie movie = optional.get();
		if (Boolean.TRUE.equals(movie.getWinner())) {
			throw new BadRequestException();
		}
		movieRepository.delete(movie);
	}

	@Override
	public void chargeDate(Reader reader) {
		Iterable<CSVRecord> lines = Util.toCSVRecord(reader);
		if (Objects.isNull(lines)) {
			throw new NotFoundException();
		}
		lines.forEach(this::chargeDate);
	}

	private void chargeDate(CSVRecord line) {
		if (line.getRecordNumber() != 1) {
			String winner = line.get("winner");
			Movie movie = movieRepository
					.save(new Movie(Integer.valueOf(line.get("yearm")), line.get("title"), winner));
			String studios = line.get("studios");
			studioService.saveStudios(movie, studios);
			String producers = line.get("producers");
			producerService.saveProducers(movie, producers);
		}
	}

}
