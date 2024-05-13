package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.dto.enums.EstablishmentType;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ConsumerPaymentDTO {

    private EstablishmentType establishmentType;

    private int establishmentNameId;

    private String establishmentName;

    private String productDescription;

    private Date dateBuy;

    private String cardNumber;

    private double amount;


}
