package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMapper {

    public ConsumerDTO convertToDTO(Consumer consumer) {

                return ConsumerDTO.builder()
                .id(consumer.getId())
                .name(consumer.getName())
                .birthDate(consumer.getBirthDate())
                .documentNumber(consumer.getDocumentNumber())
                .contact(Contact.builder()
                        .email(consumer.getContact().getEmail())
                        .mobilePhoneNumber(consumer.getContact().getMobilePhoneNumber())
                        .phoneNumber(consumer.getContact().getPhoneNumber())
                        .residencePhoneNumber(consumer.getContact().getResidencePhoneNumber())
                        .build())
                .address(Address.builder()
                        .city(consumer.getAddress().getCity())
                        .street(consumer.getAddress().getStreet())
                        .number(consumer.getAddress().getNumber())
                        .country(consumer.getAddress().getCountry())
                        .postalCode(consumer.getAddress().getPostalCode())
                        .build())
                .build();

    }
}
