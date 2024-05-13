package br.com.alelo.consumer.consumerpat.config;

import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Throwable ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(Throwable ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
