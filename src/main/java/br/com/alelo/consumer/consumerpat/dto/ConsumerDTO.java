package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ConsumerDTO {

    private Integer id;

    private String name;

    private String documentNumber;

    private LocalDate birthDate;

    private Contact contact;

    private Address address;

}
