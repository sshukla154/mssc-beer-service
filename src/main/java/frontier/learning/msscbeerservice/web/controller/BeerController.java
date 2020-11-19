package frontier.learning.msscbeerservice.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import frontier.learning.msscbeerservice.service.BeerService;
import frontier.learning.msscbeerservice.web.model.BeerDTO;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	
	BeerService beerService;

	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID beerId) {
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDTO> createBeer(@RequestBody BeerDTO beerDTO) {
		BeerDTO savedBeer = beerService.createBeer(beerDTO);
		return new ResponseEntity<BeerDTO>(savedBeer, HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDTO> updateBeerById(@PathVariable UUID beerId, @RequestBody BeerDTO beerDTO) {
		beerService.updateBeerById(beerId, beerDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
