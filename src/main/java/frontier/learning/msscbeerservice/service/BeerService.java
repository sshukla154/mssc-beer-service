package frontier.learning.msscbeerservice.service;

import java.util.UUID;

import frontier.learning.msscbeerservice.web.model.BeerDTO;

public interface BeerService {

	BeerDTO getBeerById(UUID beerId);

	BeerDTO createBeer(BeerDTO beerDTO);

	BeerDTO updateBeerById(UUID beerId, BeerDTO beerDTO);

}
