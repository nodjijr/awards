package com.nodji.awards.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProducerPrizesDTO {

	private String producer;

	private Integer interval;

	private Integer previousWin;

	private Integer followingWin;

	public ProducerPrizesDTO(String producer, Integer interval, Integer previousWin, Integer followingWin) {
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

}
