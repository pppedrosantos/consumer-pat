package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.ConsumerPaymentDTO;
import br.com.alelo.consumer.consumerpat.dto.RequestBalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.ExtractMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    ExtractMapper extractMapper;

    @Transactional
    public ResponseEntity<String> setBalance(RequestBalanceDTO requestBalanceDTO){

        Consumer consumer;

        try {
            consumer = repository.findByDrugstoreNumber(requestBalanceDTO.getCardNumber());
            if (consumer != null) {
                // é cartão de farmácia;
                consumer.getCardType().setDrugstoreCardBalance(consumer.getCardType().getDrugstoreCardBalance() + requestBalanceDTO.getAmount());
                repository.save(consumer);
            } else {
                consumer = repository.findByFoodCardNumber(requestBalanceDTO.getCardNumber());
                if (consumer != null) {
                    // é cartão de refeição
                    consumer.getCardType().setFoodCardBalance(consumer.getCardType().getFoodCardBalance() + requestBalanceDTO.getAmount());
                    repository.save(consumer);
                } else {
                    // É cartão de combustivel
                    consumer = repository.findByFuelCardNumber(requestBalanceDTO.getCardNumber());
                    consumer.getCardType().setFuelCardBalance(consumer.getCardType().getFuelCardBalance() + requestBalanceDTO.getAmount());
                    repository.save(consumer);
                }
            }
        } catch (Exception e){
            throw new NotFoundException("Card not found.");
        }

        return ResponseEntity.ok().build();
    }


@Transactional
public ResponseEntity<?> buy(ConsumerPaymentDTO consumerPaymentDTO){

    EstablishmentType establishmentType = consumerPaymentDTO.getEstablishmentType();

    switch (establishmentType) {
        case FOOD:
            return buyFromFoodEstablishment(consumerPaymentDTO);
        case DRUGSTORE:
            return buyFromDrugstore(consumerPaymentDTO);
        case FUEL:
            return buyFromFuelStation(consumerPaymentDTO);
        default:
            throw new BadRequestException("Invalid establishment type.");
    }
}

    private ResponseEntity<?> buyFromFoodEstablishment(ConsumerPaymentDTO consumerPaymentDTO) {
        Consumer consumer = repository.findByFoodCardNumber(consumerPaymentDTO.getCardNumber());
        if (consumer == null) {
            throw new NotFoundException("Food card not found.");
        }

        Double cashback = (consumerPaymentDTO.getAmount() / 100) * 10;
        Double value = consumerPaymentDTO.getAmount() - cashback;

        if (consumer.getCardType().getFoodCardBalance() < value) {
            throw new BadRequestException("Insufficient balance on the food card.");
        }

        consumer.getCardType().setFoodCardBalance(consumer.getCardType().getFoodCardBalance() - value);
        repository.save(consumer);

        saveExtract(consumerPaymentDTO);

        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> buyFromDrugstore(ConsumerPaymentDTO consumerPaymentDTO) {
        Consumer consumer = repository.findByDrugstoreNumber(consumerPaymentDTO.getCardNumber());
        if (consumer == null) {
            throw new NotFoundException("DrugstoreCard not found.");
        }

        if (consumer.getCardType().getDrugstoreCardBalance() < consumerPaymentDTO.getAmount()) {
            throw new BadRequestException("Insufficient balance on the drugstore card.");
        }

        consumer.getCardType().setDrugstoreCardBalance(consumer.getCardType().getDrugstoreCardBalance() - consumerPaymentDTO.getAmount());
        repository.save(consumer);

        saveExtract(consumerPaymentDTO);

        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> buyFromFuelStation(ConsumerPaymentDTO consumerPaymentDTO) {
        Consumer consumer = repository.findByFuelCardNumber(consumerPaymentDTO.getCardNumber());
        if (consumer == null) {
            throw new NotFoundException("Fuelcard not found.");
        }

        Double tax = (consumerPaymentDTO.getAmount() / 100) * 35;
        Double value = consumerPaymentDTO.getAmount() + tax;

        if (consumer.getCardType().getFuelCardBalance() < value) {
            throw new BadRequestException("Insufficient balance on the fuel card.");

        }

        consumer.getCardType().setFuelCardBalance(consumer.getCardType().getFuelCardBalance() - value);
        repository.save(consumer);

        saveExtract(consumerPaymentDTO);

        return ResponseEntity.ok().build();
    }


    private void saveExtract(ConsumerPaymentDTO consumerPaymentDTO) {
        Extract extract = extractMapper.convertToExtract(consumerPaymentDTO);
        extractRepository.save(extract);
    }


}
