package com.nodji.awards;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getMoviesByYearTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movie/2017").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.*.title", hasItem(is("The Emoji Movie"))));
	}

	@Test
	void getYearsWithMoreThanOneWinnersTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movie/years").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.*.*.year", hasItem(is(1986))))
				.andExpect(jsonPath("$.*.*.winnerCount", hasItem(is(2))));
	}

	@Test
	void removeTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/movie/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void removeBadRequestTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/movie/26").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void removeNoContentTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/movie/300").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
