package br.com.alelo.consumer.consumerpat.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CardTypeDTO {

    private String foodCardNumber;

    private String fuelCardNumber;

    private String drugstoreNumber;

}
