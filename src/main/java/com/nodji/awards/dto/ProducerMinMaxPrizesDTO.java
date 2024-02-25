package com.nodji.awards.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProducerMinMaxPrizesDTO {

	private List<ProducerPrizesDTO> min = new ArrayList<>();

	private List<ProducerPrizesDTO> max = new ArrayList<>();

	public ProducerMinMaxPrizesDTO(LinkedList<ProducerPrizesDTO> lista) {
		this.min.add(lista.getFirst());
		this.max.add(lista.getLast());
	}

	public void addMin(ProducerPrizesDTO min) {
		this.getMin().add(min);
	}

	public void addMax(ProducerPrizesDTO max) {
		this.getMax().add(max);
	}

}
