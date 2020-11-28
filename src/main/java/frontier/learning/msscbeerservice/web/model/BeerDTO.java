package frontier.learning.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

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

	@Null
	private Integer version;

	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime createDate;

	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	private String beerName;

	@NotBlank
	private BeerStyleEnum beerStyleName;

	@Positive
	@NotNull
	private Long upc;

	private Integer quantityOnHand;

	@Positive
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal price;

}
