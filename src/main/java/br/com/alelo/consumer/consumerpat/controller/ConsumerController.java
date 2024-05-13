package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@Controller
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @GetMapping(value = "/consumerList")
    public ResponseEntity<Page<ConsumerDTO>> getAllConsumers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ConsumerDTO> consumersPage = consumerService.getAllConsumers(page, size);
        return ResponseEntity.ok(consumersPage);
    }

    @PostMapping("/createConsumer")
    public ResponseEntity<?> createConsumer(@Valid @RequestBody Consumer consumer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consumerService.createConsumer(consumer));
    }

    @PutMapping(value = "/updateConsumer/{id}")
    public ResponseEntity<?> updateConsumer(@RequestBody ConsumerUpdateDTO consumerUpdate,
                                            @PathVariable("id") Integer id) {
        return ResponseEntity.ok(consumerService.updateConsumer(id, consumerUpdate));
    }

}
