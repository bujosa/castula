package com.bujosa.castula.common.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bujosa.castula.common.dto.MessageDto;
import com.bujosa.castula.common.functions.Operations;

@RestControllerAdvice
public class GlobalException {

    /**
     * Handles NotFoundException and returns a ResponseEntity with a MessageDto
     * containing the HttpStatus.NOT_FOUND and the exception message.
     *
     * @param ex the NotFoundException to handle
     * @return a ResponseEntity with a MessageDto containing the
     *         HttpStatus.NOT_FOUND and the exception message
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageDto> notFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDto(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    /**
     * Handles AttributeException and returns a ResponseEntity with a bad request
     * status and a MessageDto containing the error message.
     * 
     * @param ex the AttributeException to handle
     * @return a ResponseEntity with a bad request status and a MessageDto
     *         containing the error message
     */
    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<MessageDto> attributeException(AttributeException ex) {
        return ResponseEntity.badRequest()
                .body(new MessageDto(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    /**
     * Handles general exceptions and returns an HTTP 500 Internal Server Error
     * response with a message.
     * 
     * @param ex the exception to handle
     * @return a ResponseEntity containing an HTTP 500 status code and a message
     *         describing the error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDto> generalException(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with a
     * MessageDto containing the error messages.
     * 
     * @param ex the MethodArgumentNotValidException to handle
     * @return a ResponseEntity with a MessageDto containing the error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDto> validationException(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest()
                .body(new MessageDto(HttpStatus.BAD_REQUEST, Operations.trimBrackets(String.join(", ", messages))));
    }

}
