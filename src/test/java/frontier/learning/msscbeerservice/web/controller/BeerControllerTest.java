package frontier.learning.msscbeerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import frontier.learning.msscbeerservice.service.BeerService;
import frontier.learning.msscbeerservice.web.model.BeerDTO;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

	@MockBean
	BeerService beerService;
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectmapper;

	@Test
	void getBeerById() throws Exception {

		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void createBeer() throws Exception {

		BeerDTO beerDTO = BeerDTO.builder().build();
		String beerDTOJtoJSON = objectmapper.writeValueAsString(beerDTO);
		mockMvc.perform(post("/api/v1/beer").content(beerDTOJtoJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	@Test
	void updateBeerById() throws Exception {

		BeerDTO beerDTO = BeerDTO.builder().build();
		String beerDTOJtoJSON = objectmapper.writeValueAsString(beerDTO);
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString()).content(beerDTOJtoJSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}

}
