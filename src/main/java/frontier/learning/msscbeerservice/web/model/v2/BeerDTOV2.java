package frontier.learning.msscbeerservice.web.model.v2;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTOV2 {

	private UUID id;
	private String beerName;
	private BeerStyleEnum beerStyle;
	private Long upc;

}
