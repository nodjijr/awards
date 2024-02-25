package com.nodji.awards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nodji.awards.model.MovieProducer;
import com.nodji.awards.model.MovieProducerId;
import com.nodji.awards.util.Constants;

public interface MovieProducerRepository extends JpaRepository<MovieProducer, MovieProducerId> {

	@Query(value = Constants.SQL_FIND_BY_MOVIE_WINNER_ORDER_PRODUCER)
	List<MovieProducer> findByMovieWinnerOrderByProducerId(Boolean isWinner);

}
