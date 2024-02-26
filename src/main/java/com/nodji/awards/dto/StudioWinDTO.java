package com.nodji.awards.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudioWinDTO {

	private String name;

	private Long winCount;

	public StudioWinDTO (String name, Long winCount) {
		this.name = name;
		this.winCount = winCount;
	}

}
