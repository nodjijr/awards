package com.nodji.awards.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
//@NoArgsConstructor
public class YearWinnerDTO {
	public YearWinnerDTO() {
	}

	private List<YearWinnerMovieDTO> years;

	public YearWinnerDTO(List<YearWinnerMovieDTO> years) {
		this.years = new ArrayList<>();
		this.years.addAll(years);
	}

}
