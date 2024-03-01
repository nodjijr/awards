package com.nodji.awards;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.nodji.awards.api.StudioController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudioControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudioController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	void getGreatestWinnersTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/studio/winners").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.*.*.name", hasItem(is("Columbia Pictures"))))
				.andExpect(jsonPath("$.*.*.winCount", hasItem(is(6))));
	}

}
