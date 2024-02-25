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
@Table(name = "MOVIE_STUDIO")
public class MovieStudio {

	@EmbeddedId
	private MovieStudioId id;

	@ManyToOne
	@MapsId("idMovie")
	private Movie movie;

	@ManyToOne
	@MapsId("idStudio")
	private Studio studio;

	public MovieStudio(Movie movie, Studio studio) {
		this.movie = movie;
		this.studio = studio;
		this.id = new MovieStudioId(movie.getId(), studio.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		MovieStudio other = (MovieStudio) obj;
		return Objects.equals(movie, other.getMovie()) && Objects.equals(studio, other.getStudio());
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, studio);
	}

}
