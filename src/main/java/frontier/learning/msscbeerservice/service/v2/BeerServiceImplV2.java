package frontier.learning.msscbeerservice.service.v2;

import java.util.UUID;

import org.springframework.stereotype.Service;

import frontier.learning.msscbeerservice.web.model.v2.BeerDTOV2;
import frontier.learning.msscbeerservice.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImplV2 implements BeerServiceV2 {

	@Override
	public BeerDTOV2 getBeerById(UUID beerId) {
		log.debug("(Version 2) Get a beer...");
		return BeerDTOV2.builder().id(UUID.randomUUID()).beerName("Galaxy Cat").beerStyle(BeerStyleEnum.GOSE).build();
	}

	@Override
	public BeerDTOV2 saveNewBeer(BeerDTOV2 beerDTOV2) {
		log.debug("(Version 2) Save a new beer...");
		return BeerDTOV2.builder().id(UUID.randomUUID()).build();		
	}

	@Override
	public void updateBeer(UUID beerId, BeerDTOV2 beerDTOV2) {
		log.debug("(Version 2) Updating a beer...");
		// TODO :  Would add a real implementation to update beer
	}

	@Override
	public void deleteById(UUID beerId) {
		log.debug("(Version 2) Deleting a beer...");
		
	}

}
