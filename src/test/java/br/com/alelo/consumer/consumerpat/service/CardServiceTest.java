package br.com.alelo.consumer.consumerpat.service;


import br.com.alelo.consumer.consumerpat.dto.ConsumerPaymentDTO;
import br.com.alelo.consumer.consumerpat.dto.RequestBalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CardServiceTest {

    @Mock
    private ConsumerRepository repository;

    @Mock
    private ExtractRepository extractRepository;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    public void setUp() {
        repository = mock(ConsumerRepository.class);
        cardService = mock(CardService.class);
    }

    @Test
    public void testSetBalance_WhenCardIsFood_ShouldUpdateFoodCardBalance() {
        // Arrange
        RequestBalanceDTO requestBalanceDTO = new RequestBalanceDTO();
        requestBalanceDTO.setCardNumber("123456");
        requestBalanceDTO.setAmount(0);

        Consumer consumer = new Consumer();
        consumer.setCardType(new CardType());
        consumer.getCardType().setFoodCardBalance(100);

        when(repository.findByDrugstoreNumber(requestBalanceDTO.getCardNumber())).thenReturn(null);
        when(repository.findByFoodCardNumber(requestBalanceDTO.getCardNumber())).thenReturn(consumer);

        // Act
        ResponseEntity<String> response = cardService.setBalance(requestBalanceDTO);

        // Assert
        assertEquals(100, consumer.getCardType().getFoodCardBalance());
    }

    @Test
    public void testBuyFromFoodEstablishment_Success() {
        // Arrange
        ConsumerPaymentDTO consumerPaymentDTO = new ConsumerPaymentDTO();
        consumerPaymentDTO.setEstablishmentType(EstablishmentType.FOOD);
        consumerPaymentDTO.setCardNumber("123456789");
        consumerPaymentDTO.setAmount(10);

        Consumer consumer = new Consumer();
        consumer.setId(1);
        CardType cardType = new CardType();
        cardType.setFoodCardNumber("123456789");
        cardType.setFoodCardBalance(100);
        consumer.setCardType(cardType);

        when(repository.findByFoodCardNumber(consumerPaymentDTO.getCardNumber())).thenReturn(consumer);
        when(cardService.buy(consumerPaymentDTO)).thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<?> response = cardService.buy(consumerPaymentDTO);

        // Assert
        assertEquals(ResponseEntity.ok().build(), response);
        assertEquals(100.0, consumer.getCardType().getFoodCardBalance());

    }


}
