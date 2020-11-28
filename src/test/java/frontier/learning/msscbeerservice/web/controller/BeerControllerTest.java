package frontier.learning.msscbeerservice.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import frontier.learning.msscbeerservice.repositories.BeerRepository;
import frontier.learning.msscbeerservice.service.BeerService;
import frontier.learning.msscbeerservice.web.model.BeerDTO;
import frontier.learning.msscbeerservice.web.model.BeerStyleEnum;

@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "frontier.learning")
public class BeerControllerTest {

	@MockBean
	BeerRepository beerRespository;
	
	@MockBean
	BeerService beerService;
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectmapper;

	@Test
	public void getBeerById() throws Exception {
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void createBeer() throws Exception {
		BeerDTO beerDTO = getValidBeerDTO();
//		beerDTO.setId(null);
		String beerDtoToJson = objectmapper.writeValueAsString(beerDTO);

		BeerDTO savedDto = BeerDTO.builder().id(UUID.randomUUID()).beerName("Near Beer").beerStyleName(BeerStyleEnum.PILSNER).build();

		given(beerService.createBeer(any())).willReturn(savedDto);

		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoToJson))
				.andExpect(status().isCreated());

	}

	@Test
	public void updateBeerById() throws Exception {
		BeerDTO beerDTO = getValidBeerDTO();
		String beerDtoToJson = objectmapper.writeValueAsString(beerDTO);

		mockMvc.perform(
				put("/api/v1/beer/" + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON).content(beerDtoToJson))
				.andExpect(status().isNoContent());

	}

	BeerDTO getValidBeerDTO() {
		return BeerDTO.builder().id(UUID.randomUUID()).beerName("Bud").beerStyleName(BeerStyleEnum.PALE_ALE)
				.upc(123321L).build();
	}

}
