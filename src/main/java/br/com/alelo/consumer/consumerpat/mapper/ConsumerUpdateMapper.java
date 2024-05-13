package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ConsumerUpdateMapper {

    public Consumer convertToDTO(ConsumerUpdateDTO consumerUpdateDTO, Consumer consumer){

        consumer.setName(consumerUpdateDTO.getName());
        consumer.setBirthDate(consumerUpdateDTO.getBirthDate());
        consumer.setDocumentNumber(consumerUpdateDTO.getDocumentNumber());

        Contact contact = new Contact();
        contact.setPhoneNumber(consumerUpdateDTO.getContact().getPhoneNumber());
        contact.setMobilePhoneNumber(consumerUpdateDTO.getContact().getMobilePhoneNumber());
        contact.setResidencePhoneNumber(consumerUpdateDTO.getContact().getResidencePhoneNumber());
        contact.setEmail(consumerUpdateDTO.getContact().getEmail());
        consumer.setContact(contact);

        Address address = new Address();
        address.setCity(consumerUpdateDTO.getAddress().getCity());
        address.setNumber(consumerUpdateDTO.getAddress().getNumber());
        address.setCountry(consumerUpdateDTO.getAddress().getCountry());
        address.setPostalCode(consumerUpdateDTO.getAddress().getPostalCode());
        address.setStreet(consumerUpdateDTO.getAddress().getStreet());
        consumer.setAddress(address);

        CardType cardType = new CardType();
        cardType.setFoodCardNumber(consumerUpdateDTO.getCardType().getFoodCardNumber());
        cardType.setFuelCardNumber(consumerUpdateDTO.getCardType().getFuelCardNumber());
        cardType.setDrugstoreNumber(consumerUpdateDTO.getCardType().getDrugstoreNumber());
        consumer.setCardType(cardType);

        return consumer;

    }
}
