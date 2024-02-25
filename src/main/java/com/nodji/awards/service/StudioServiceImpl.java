package com.nodji.awards.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nodji.awards.dto.StudioDTO;
import com.nodji.awards.model.Movie;
import com.nodji.awards.model.MovieStudio;
import com.nodji.awards.model.Studio;
import com.nodji.awards.repository.MovieStudioRepository;
import com.nodji.awards.repository.StudioRepository;

@Service
public class StudioServiceImpl implements StudioService {

	Logger logger = LoggerFactory.getLogger(StudioServiceImpl.class);

	@Autowired
	private StudioRepository studioRepository;

	@Autowired
	private MovieStudioRepository movieStudioRepository;

	@Override
	public void saveStudios(Movie movie, String studios) {
		for (String strStudio : studios.split(",|\\ and ")) {
			Studio studio = new Studio(strStudio.trim());

			Example<Studio> example = Example.of(studio);

			if (studioRepository.exists(example)) {
				studio = studioRepository.findByName(strStudio.trim());
			} else {
				studio = studioRepository.save(studio);
			}

			movieStudioRepository.save(new MovieStudio(movie, studio));
		}
	}

	@Override
	public StudioDTO getGreatestWinners() {
		return new StudioDTO(studioRepository.findByWinners());
	}

}
