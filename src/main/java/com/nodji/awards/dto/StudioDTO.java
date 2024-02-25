package com.nodji.awards.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudioDTO {

	private List<StudioWinDTO> studios;

	public StudioDTO(List<StudioWinDTO> winners) {
		this.studios = new ArrayList<>();
		this.studios.addAll(winners);
	}

}
