package frontier.learning.msscbeerservice.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import frontier.learning.msscbeerservice.domain.Beer;
import frontier.learning.msscbeerservice.repositories.BeerRepository;
import frontier.learning.msscbeerservice.web.exception.NotFoundException;
import frontier.learning.msscbeerservice.web.mapper.BeerMapper;
import frontier.learning.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beermapper;

	@Override
	public BeerDTO getBeerById(UUID beerId) {
		log.debug("BeerServiceImpl.getBeerById()...");
		Beer savedBeer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		return beermapper.beerToBeerDTO(savedBeer);

	}

	@Override
	public BeerDTO createBeer(BeerDTO beerDTO) {
		log.debug("BeerServiceImpl.createBeer()...");
		Beer beerfromDTO = beerRepository.save(beermapper.beerDTOToBeer(beerDTO));
		return beermapper.beerToBeerDTO(beerfromDTO);
	}

	@Override
	public void updateBeerById(UUID beerId, BeerDTO beerDTO) {
		log.debug("BeerServiceImpl.updateBeerById()...");
		Beer savedBeer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		savedBeer.setBeerName(beerDTO.getBeerName());
		savedBeer.setBeerStyle(beerDTO.getBeerStyleName().toString());
		savedBeer.setPrice(beerDTO.getPrice());
		savedBeer.setUpc(beerDTO.getUpc());
		beerRepository.save(savedBeer);
	}

	@Override
	public void deleteById(UUID beerId) {
		log.debug("BeerServiceImpl.deleteById()...");
		beerRepository.deleteById(beerId);
	}

	/*
	 * @Override public BeerDTO getBeerById(UUID beerId) {
	 * log.debug("Get a beer..."); return
	 * BeerDTO.builder().id(UUID.randomUUID()).beerName("Galaxy Cat").beerStyleName(
	 * BeerStyleEnum.ALE)
	 * .version(123).lastModifiedDate(OffsetDateTime.now()).createDate(
	 * OffsetDateTime.now()).upc(1221340798L) .quantityOnHand(5).price(new
	 * BigDecimal(154.00)).build(); }
	 * 
	 * @Override public BeerDTO createBeer(BeerDTO beerDTO) {
	 * log.debug("Save a new beer..."); return
	 * BeerDTO.builder().id(UUID.randomUUID()).beerName(beerDTO.getBeerName())
	 * .beerStyleName(beerDTO.getBeerStyleName()).version(beerDTO.getVersion())
	 * .lastModifiedDate(OffsetDateTime.now()).createDate(OffsetDateTime.now()).upc(
	 * beerDTO.getUpc())
	 * .quantityOnHand(beerDTO.getQuantityOnHand()).price(beerDTO.getPrice()).build(
	 * ); }
	 * 
	 * @Override public void updateBeerById(UUID beerId, BeerDTO beerDTO) {
	 * log.debug("Updating a beer..."); // TODO : Would add a real implementation to
	 * update beer }
	 * 
	 * @Override public void deleteById(UUID beerId) {
	 * log.debug("Deleting a beer...");
	 * 
	 * }
	 */
}
