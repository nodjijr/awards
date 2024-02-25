package com.nodji.awards.service;

import com.nodji.awards.dto.StudioDTO;
import com.nodji.awards.model.Movie;

public interface StudioService {

	void saveStudios(Movie movie, String studios);

	StudioDTO getGreatestWinners();
}
