package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerUpdateMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ConsumerUpdateMapper consumerUpdateMapper;

    @Autowired
    ConsumerMapper consumerMapper;


    @Transactional
    public Page<ConsumerDTO> getAllConsumers(int page, int size) {
        Page<Consumer> consumersPage = repository.findAll(PageRequest.of(page, size));
        return consumersPage.map(consumerMapper::convertToDTO);

    }

    @Transactional
    public ResponseEntity<String> updateConsumer(Integer id, ConsumerUpdateDTO requestBody) {

        Consumer consumer = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Consumer not found.")
        );
        consumer = consumerUpdateMapper.convertToDTO(requestBody, consumer);
        repository.save(consumer);

        return ResponseEntity.ok("Updated successfully.");

    }

    @Transactional
    public ResponseEntity<?> createConsumer(@Valid Consumer consumer){

        if (repository.existsByDocumentNumber(consumer.getDocumentNumber())
                || repository.existsByContactEmail(consumer.getContact().getEmail())
                || repository.existsByContactMobilePhoneNumber(consumer.getContact().getMobilePhoneNumber())) {
            throw new BadRequestException("One or more fields already exist.");
        }

        repository.save(consumer);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumer);

    }
}
