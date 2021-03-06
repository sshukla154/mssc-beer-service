package frontier.learning.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import frontier.learning.msscbeerservice.service.BeerService;
import frontier.learning.msscbeerservice.service.BeerServiceImpl;
import frontier.learning.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * @Validated - Its a Spring Framework annotation which performs validation on METHOD INPUT PARAMTERS 
 * 
 * Eg: @NotNUll in getBeerById()
 * Eg: @NOtNull in createBeer()
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDTO> getBeerById(@NotNull @PathVariable UUID beerId) {
		log.debug("BeerController.getBeerById()...");
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDTO> createBeer(@NotNull @Validated @RequestBody BeerDTO beerDTO) {
		log.debug("BeerController.createBeer()...");
		BeerDTO savedBeer = beerService.createBeer(beerDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		// Add hostname to URL
		httpHeaders.add("Location", "/api/v1/beer" + savedBeer.getId().toString());
		return new ResponseEntity<BeerDTO>(httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDTO> updateBeerById(@PathVariable UUID beerId, @Validated @RequestBody BeerDTO beerDTO) {
		log.debug("BeerController.updateBeerById()...");
		beerService.updateBeerById(beerId, beerDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{beerId}")
//	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable UUID beerId) {
		log.debug("BeerController.deleteBeer()...");
		beerService.deleteById(beerId);
	}

	/*
	 * NOTE : (This validateExceptionhandler() has been moved to Advice class) This
	 * ExceptionHandler(), will be called from create/update beer API. When any
	 * exception is occurred in these API that will be cached by @Valid annotation
	 * which will internally call this API
	 */
//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<List> validateErrorHandler(ConstraintViolationException e) {
//		List<String> errors = new ArrayList<String>(e.getConstraintViolations().size());
//		Consumer<ConstraintViolation> consumerAction = new Consumer<ConstraintViolation>() {
//
//			@Override
//			public void accept(ConstraintViolation constraintViolation) {
//				errors.add(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
//			}
//		};
//		e.getConstraintViolations().forEach(consumerAction);
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}

}
