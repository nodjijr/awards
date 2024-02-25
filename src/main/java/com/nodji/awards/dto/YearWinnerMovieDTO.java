package com.nodji.awards.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YearWinnerMovieDTO {
	
	private Integer year;
	
	private Long winnerCount;
	
	public YearWinnerMovieDTO(Integer year, Long winnerCount) {
		this.year = year;
		this.winnerCount = winnerCount;
	}

}
