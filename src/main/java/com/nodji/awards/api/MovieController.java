package com.nodji.awards.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nodji.awards.dto.MovieDTO;
import com.nodji.awards.dto.YearWinnerDTO;
import com.nodji.awards.exception.BadRequestException;
import com.nodji.awards.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/movie")
@Tag(name = "Movie", description = "the Movie Api")
public class MovieController {

	Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	/**
	 * @return {@link list of MovieDTO}
	 */
	@Operation(summary = "Buscar filme premiado por ano", description = "buscar as entidades dos filmes premiados por ano e seus dados da base de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso") })
	@GetMapping("/{year}")
	public ResponseEntity<List<MovieDTO>> getMovies(@PathVariable(name = "year") Integer year) {
		List<MovieDTO> movies = movieService.getMoviesByYear(year);
		HttpStatus status = HttpStatus.OK;
		if (movies.isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(movies, status);
	}

	/**
	 * @return {@link YearWinnerDTO}
	 */
	@Operation(summary = "Buscar os anos premiados", description = "buscar as entidades dos filmes premiados nos anos com mais de um vencedor e seus dados da base de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso") })
	@GetMapping("/years")
	public ResponseEntity<YearWinnerDTO> getYearsWithMoreThanOneWinners() {
		YearWinnerDTO dto = movieService.getYearsWithMoreThanOneWinners();
		HttpStatus status = HttpStatus.OK;
		if (dto.getYears().isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(dto, status);
	}

	@Operation(summary = "Remover filme", description = "remover uma entidade de filme especifica da base de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeMovie(@PathVariable(name = "id") Long id) throws BadRequestException {
		movieService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
