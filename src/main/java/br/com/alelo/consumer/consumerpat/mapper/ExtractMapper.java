package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.ConsumerPaymentDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.springframework.stereotype.Component;

@Component
public class ExtractMapper {

    public Extract convertToExtract(ConsumerPaymentDTO consumerPaymentDTO){

        return Extract.builder()
                .cardNumber(consumerPaymentDTO.getCardNumber())
                .amount(consumerPaymentDTO.getAmount())
                .dateBuy(consumerPaymentDTO.getDateBuy())
                .productDescription(consumerPaymentDTO.getProductDescription())
                .establishmentName(consumerPaymentDTO.getEstablishmentName())
                .establishmentNameId(consumerPaymentDTO.getEstablishmentNameId())
                .build();

    }
}
