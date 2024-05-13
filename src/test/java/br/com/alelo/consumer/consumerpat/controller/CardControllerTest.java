package br.com.alelo.consumer.consumerpat.controller;


import br.com.alelo.consumer.consumerpat.dto.ConsumerPaymentDTO;
import br.com.alelo.consumer.consumerpat.dto.RequestBalanceDTO;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Mock
    private ConsumerRepository repository;

    @Mock
    private ExtractRepository extractRepository;

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController controller;

    @BeforeEach
    public void setUp() {
        repository = mock(ConsumerRepository.class);
    }


    @Test
    public void testSetBalance() {
        // Arrange
        RequestBalanceDTO requestBalanceDTO = new RequestBalanceDTO();
        when(cardService.setBalance(requestBalanceDTO)).thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<?> response = controller.setBalance(requestBalanceDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cardService, times(1)).setBalance(requestBalanceDTO);
    }

    @Test
    public void testBuy() {
        // Arrange
        ConsumerPaymentDTO consumerPaymentDTO = new ConsumerPaymentDTO();
        when(cardService.buy(consumerPaymentDTO)).thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<?> response = controller.buy(consumerPaymentDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cardService, times(1)).buy(consumerPaymentDTO);
    }
}
