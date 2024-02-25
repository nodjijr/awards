package com.nodji.awards.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOVIE")
@Data
@NoArgsConstructor
public class Movie {

	@Id
	@Column(name = "ID_MOVIE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "YEARM", nullable = false)
	private Integer yearm;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "IS_WINNER", nullable = false)
	private Boolean winner;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<MovieStudio> studios = new HashSet<>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<MovieProducer> producers = new HashSet<>();

	public Movie(Integer yearm, String title, String winner) {
		this.yearm = yearm;
		this.title = title;
		this.winner = (winner != null && "yes".equalsIgnoreCase(winner));
	}

	@Override
	public String toString() {
		return "Year: " + getYearm() + " - Title: " + getTitle() + " - Winner: " + getWinner();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Movie other = (Movie) obj;
		return Objects.equals(id, other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, yearm);
	}
}
