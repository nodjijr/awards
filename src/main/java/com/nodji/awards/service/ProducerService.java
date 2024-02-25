package com.nodji.awards.service;

import com.nodji.awards.dto.ProducerMinMaxPrizesDTO;
import com.nodji.awards.model.Movie;

public interface ProducerService {

	void saveProducers(Movie movie, String producers);

	ProducerMinMaxPrizesDTO getMaxAndMinPrizes();

}
