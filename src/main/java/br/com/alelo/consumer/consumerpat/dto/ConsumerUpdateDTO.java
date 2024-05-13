package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ConsumerUpdateDTO {

    private String name;

    private String documentNumber;

    private LocalDate birthDate;

    private Contact contact;

    private Address address;

    private CardTypeDTO cardType;

}
