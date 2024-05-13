package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.mock.ConsumerMock;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ConsumerService consumerService;

    @InjectMocks
    private ConsumerController consumerController;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCreateConsumerError() throws Exception {

        mockMvc.perform(post("/consumer/createConsumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(anyString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateConsumerSuccess() throws Exception {

        String json = objectMapper.writeValueAsString(ConsumerMock.getConsumerMock());

        mockMvc.perform(post("/consumer/createConsumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateConsumer() throws Exception {
        Integer id = 1;
        ConsumerUpdateDTO consumerUpdate = new ConsumerUpdateDTO();

        ResponseEntity<String> responseEntity = ResponseEntity.ok().build();
        when(consumerService.updateConsumer(id, consumerUpdate)).thenReturn(responseEntity);

        ResponseEntity<?> response = consumerController.updateConsumer(consumerUpdate, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseEntity, response.getBody());
        verify(consumerService, times(1)).updateConsumer(id, consumerUpdate);
    }
}
