package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerPaymentDTO;
import br.com.alelo.consumer.consumerpat.dto.RequestBalanceDTO;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RestController
@RequestMapping("/consumer")
public class CardController {

    @Autowired
    CardService service;

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @PutMapping(value = "/setCardBalance")
    public ResponseEntity<?> setBalance(@RequestBody RequestBalanceDTO requestBalanceDTO) {
        return ResponseEntity.ok().body(service.setBalance(requestBalanceDTO));
    }

        /*
         * Débito de valor no cartão (compra)
         *
         * establishmentType: tipo do estabelecimento comercial
         * establishmentName: nome do estabelecimento comercial
         * cardNumber: número do cartão
         * productDescription: descrição do produto
         * value: valor a ser debitado (subtraído)
         */
    @PostMapping(value = "/buy")
    public ResponseEntity<?> buy(@RequestBody ConsumerPaymentDTO consumerPaymentDTO){
        return ResponseEntity.ok().body(service.buy(consumerPaymentDTO));
    }
}