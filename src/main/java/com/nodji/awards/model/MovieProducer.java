package com.nodji.awards.model;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MOVIE_PRODUCER")
public class MovieProducer {

	@EmbeddedId
	private MovieProducerId id;

	@ManyToOne
	@MapsId("idMovie")
	private Movie movie;

	@ManyToOne
	@MapsId("idProducer")
	private Producer producer;

	public MovieProducer(Movie movie, Producer producer) {
		this.movie = movie;
		this.producer = producer;
		this.id = new MovieProducerId(movie.getId(), producer.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		MovieProducer other = (MovieProducer) obj;
		return Objects.equals(movie, other.getMovie()) && Objects.equals(producer, other.getProducer());
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, producer);
	}

}
