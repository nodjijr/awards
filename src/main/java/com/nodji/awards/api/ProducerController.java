package com.nodji.awards.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nodji.awards.dto.ProducerMinMaxPrizesDTO;
import com.nodji.awards.service.ProducerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("producer")
@Tag(name = "Producer", description = "the Producer Api")
public class ProducerController {

	Logger logger = LoggerFactory.getLogger(ProducerController.class);

	@Autowired
	private ProducerService producerService;

	/**
	 * @return {@link list of ProducerMinMaxPrizesDTO}
	 */
	@Operation(summary = "Buscar produtores premiados", description = "Obtém os produtores premiados, com maior e menor periodo de anos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso") })
	@GetMapping("interval-prizes")
	public ResponseEntity<ProducerMinMaxPrizesDTO> getMaxAndMinPrizes() {
		ProducerMinMaxPrizesDTO dto = producerService.getMaxAndMinPrizes();
		HttpStatus status = HttpStatus.OK;
		if (dto.getMax().isEmpty() && dto.getMin().isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(dto, status);
	}

}
