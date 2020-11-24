package frontier.learning.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO {

	@Null
	private UUID id;

	@Positive
	@Null
	private Integer version;

	@Null
	private OffsetDateTime createDate;

	@Null
	private OffsetDateTime lastModifiedDate;

	@NotNull
	private String beerName;

	@NotNull
	private BeerStyleEnum beerStyleName;

	@Positive
	@NotNull
	private Long upc;

	@Positive
	private Integer quantityOnHand;

	@Positive
	private BigDecimal price;

}
