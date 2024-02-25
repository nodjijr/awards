package com.nodji.awards.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "STUDIO")
public class Studio {

	@Id
	@Column(name = "ID_STUDIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", length = 50, unique = true)
	private String name;

	@OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MovieStudio> movies = new ArrayList<>();

	public Studio(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Studio: " + getName();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Studio other = (Studio) obj;
		return Objects.equals(id, other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

}
