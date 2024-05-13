package br.com.alelo.consumer.consumerpat.mock;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import org.mockito.Mock;

import java.time.LocalDate;


import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ConsumerMock {

    // Cria um objeto Consumer mockado


    public static Consumer getConsumerMock() {

        return Consumer.builder()
                .id(1)
                .name("cliente")
                .birthDate(LocalDate.now())
                .documentNumber("123")
                .contact(Contact.builder()
                        .email("teste@teste")
                        .mobilePhoneNumber("123")
                        .phoneNumber("123")
                        .residencePhoneNumber("123")
                        .build())
                .address(Address.builder()
                        .city("city")
                        .street("street")
                        .number(1)
                        .country("country")
                        .postalCode("123")
                        .build())
                .cardType(CardType.builder()
                        .fuelCardBalance(1.1)
                        .fuelCardNumber("123")
                        .drugstoreCardBalance(100.0)
                        .fuelCardNumber("321")
                        .drugstoreNumber("132")
                        .drugstoreCardBalance(57.0)
                        .build())
                .build();
    }
}
