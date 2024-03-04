package com.nodji.awards.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nodji.awards.dto.StudioDTO;
import com.nodji.awards.service.StudioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/studio")
@Tag(name = "Studio", description = "the Studio Api")
public class StudioController {

	Logger logger = LoggerFactory.getLogger(StudioController.class);

	@Autowired
	private StudioService studioService;

	/**
	 * @return {@link list of StudioDTO}
	 */
	@Operation(summary = "Buscar estudios premiados", description = "Obtém os estudios premiados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso") })
	@GetMapping("/winners")
	public ResponseEntity<StudioDTO> getGreatestWinners() {
		StudioDTO dto = studioService.getGreatestWinners();
		HttpStatus status = HttpStatus.OK;
		if (dto.getStudios() == null || dto.getStudios().isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(dto, status);
	}

}
