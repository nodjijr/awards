package com.nodji.awards.dto;

import java.util.ArrayList;
import java.util.List;

import com.nodji.awards.model.Movie;
import com.nodji.awards.model.MovieProducer;
import com.nodji.awards.model.MovieStudio;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDTO {

	private Long id;

	private Integer yearm;

	private String title;

	private List<String> studios = new ArrayList<>();

	private List<String> producers = new ArrayList<>();

	private Boolean winner;

	public MovieDTO(Movie movie) {
		this.id = movie.getId();
		this.yearm = movie.getYearm();
		this.title = movie.getTitle();
		this.winner = movie.getWinner();

		for (MovieStudio ms : movie.getStudios()) {
			this.studios.add(ms.getStudio().getName());
		}

		for (MovieProducer mp : movie.getProducers()) {
			this.producers.add(mp.getProducer().getName());
		}
	}

}
