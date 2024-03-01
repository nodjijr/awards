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
class ProducerControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getGreatestWinnersTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/producer/interval-prizes").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.min.*.producer", hasItem(is("Joel Silver"))))
				.andExpect(jsonPath("$.max.*.producer", hasItem(is("Matthew Vaughn"))));
	}

}
