package frontier.learning.msscbeerservice.web.mapper;

import org.mapstruct.Mapper;

import frontier.learning.msscbeerservice.domain.Beer;
import frontier.learning.msscbeerservice.web.model.BeerDTO;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

	BeerDTO beerToBeerDTO(Beer beer);

	Beer beerDTOToBeer(BeerDTO beerDTO);

}
