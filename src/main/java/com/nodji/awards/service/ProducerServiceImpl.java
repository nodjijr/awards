package com.nodji.awards.service;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nodji.awards.dto.ProducerMinMaxPrizesDTO;
import com.nodji.awards.dto.ProducerPrizesDTO;
import com.nodji.awards.model.Movie;
import com.nodji.awards.model.MovieProducer;
import com.nodji.awards.model.Producer;
import com.nodji.awards.repository.MovieProducerRepository;
import com.nodji.awards.repository.ProducerRepository;

@Service
public class ProducerServiceImpl implements ProducerService {

	Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

	@Autowired
	private ProducerRepository producerRepository;

	@Autowired
	private MovieProducerRepository movieProducerRepository;

	@Override
	public void saveProducers(Movie movie, String producers) {
		Stream.of(producers.split(",|\\ and ")).forEach(value -> movieProducerRepository.save(new MovieProducer(movie, validateProducer(value))));
	}

	@Override
	public ProducerMinMaxPrizesDTO getMaxAndMinPrizes() {
		List<MovieProducer> mpList = movieProducerRepository.findByMovieWinnerOrderByProducerId(true);
		ProducerMinMaxPrizesDTO dto = new ProducerMinMaxPrizesDTO();
		dto.addMin(findMinInterval(mpList));
		dto.addMax(findMaxInterval(mpList));
		return dto;
	}

	private Producer validateProducer(String value) {
		Producer producer = new Producer(value.trim());
		Example<Producer> example = Example.of(producer);
		if (producerRepository.exists(example)) {
			return producerRepository.findByName(value.trim());
		}
		return producerRepository.save(producer);
	}


	
	private ProducerPrizesDTO findMinInterval(List<MovieProducer> mpList) {
		ProducerPrizesDTO min = new ProducerPrizesDTO(null, Integer.MAX_VALUE, null, null);
		for (int i = 0; i < mpList.size() - 1; i++) {
			for (int j = i + 1; j < mpList.size(); j++) {
				MovieProducer mpi = mpList.get(i);
				MovieProducer mpj = mpList.get(j);
				if (mpi.getProducer().equals(mpj.getProducer())) {
					Integer interval = Math.abs(mpi.getMovie().getYearm() - mpj.getMovie().getYearm());
					if (interval < min.getInterval()) {
						min.setInterval(interval);
						min.setProducer(mpi.getProducer().getName());
						min.setPreviousWin(mpi.getMovie().getYearm());
						min.setFollowingWin(mpj.getMovie().getYearm());
						break;
					}
				}
			}
		}
		return min;
	}

	private ProducerPrizesDTO findMaxInterval(List<MovieProducer> mpList) {
		ProducerPrizesDTO max = new ProducerPrizesDTO(null, Integer.MIN_VALUE, null, null);
		for (int i = 0; i < mpList.size() - 1; i++) {
			for (int j = i + 1; j < mpList.size(); j++) {
				MovieProducer mpi = mpList.get(i);
				MovieProducer mpj = mpList.get(j);
				if (mpi.getProducer().equals(mpj.getProducer())) {
					Integer interval = Math.abs(mpi.getMovie().getYearm() - mpj.getMovie().getYearm());
					if (interval > max.getInterval()) {
						max.setInterval(interval);
						max.setProducer(mpi.getProducer().getName());
						max.setPreviousWin(mpi.getMovie().getYearm());
						max.setFollowingWin(mpj.getMovie().getYearm());
						break;
					}
				}
			}
		}
		return max;
	}

}
