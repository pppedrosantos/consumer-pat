package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerUpdateMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsumerServiceTest {

    @Mock
    private ConsumerService consumerService;

    @Mock
    private ConsumerRepository repository;

    @Mock
    private ConsumerUpdateMapper consumerUpdateMapper;

    @InjectMocks
    ConsumerController consumerController;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createConsumer_ValidConsumer_ReturnsCreatedResponse(){
        // Arrange
        Consumer consumer = new Consumer();
        consumer.setDocumentNumber("123456789");
        consumer.setName("teste");
        consumer.setBirthDate(LocalDate.now());
        consumer.setCardType(new CardType("1", 0.0, "2", 0.0, "3", 0.0));
        consumer.setAddress(new Address("string", 1, "dds", "sswsw", "ssaas"));
        consumer.setContact(new Contact("1", "2", "3", "teste@teste"));


        when(repository.existsByDocumentNumber(consumer.getDocumentNumber())).thenReturn(false);
        when(repository.existsByContactEmail(consumer.getContact().getEmail())).thenReturn(false);
        when(repository.existsByContactMobilePhoneNumber(consumer.getContact().getMobilePhoneNumber())).thenReturn(false);

        // Act
        ResponseEntity<?> response = consumerController.createConsumer(consumer);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(consumerService, times(1)).createConsumer(consumer);

    }

    @Test
    public void testUpdateConsumer_Success() {
        // Arrange
        Integer id = 1;
        ConsumerUpdateDTO requestBody = new ConsumerUpdateDTO();
        Consumer consumer = new Consumer();
        consumer.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(consumer));
        when(consumerUpdateMapper.convertToDTO(requestBody, consumer)).thenReturn(consumer);

        // Act
        ResponseEntity<?> response = consumerController.updateConsumer(requestBody, id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
