package com.nodji.awards;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nodji.awards.api.StudioController;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class StudioControllerIntegrationTest {

	private MockMvc mockMvc;

	@Autowired
	private StudioController studioController;

	@Autowired
	protected WebApplicationContext wac;

	@Before(value = "")
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.studioController).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getGreatestWinnersTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/studio/winners").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.*.*.name", hasItem(is("Columbia Pictures"))))
				.andExpect(jsonPath("$.*.*.winCount", hasItem(is(6))));
	}

}
