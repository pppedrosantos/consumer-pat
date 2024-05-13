package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface ConsumerService {

    Page<ConsumerDTO> getAllConsumers(int page, int size);

    ResponseEntity<?> createConsumer(@Valid Consumer consumer);

    ResponseEntity<String> updateConsumer(Integer id, ConsumerUpdateDTO requestBody);


}
