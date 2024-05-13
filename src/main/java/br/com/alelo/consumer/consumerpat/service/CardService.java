package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerPaymentDTO;
import br.com.alelo.consumer.consumerpat.dto.RequestBalanceDTO;
import org.springframework.http.ResponseEntity;

public interface CardService {

    ResponseEntity<?> buy(ConsumerPaymentDTO consumerPaymentDTO);

    ResponseEntity<String> setBalance(RequestBalanceDTO requestBalanceDTO);
}
