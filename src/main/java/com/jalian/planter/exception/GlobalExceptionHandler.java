package com.jalian.planter.exception;

import com.jalian.planter.exception.msg.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseMessage> handleBadRequestException (HttpServletRequest request, BadRequestException bre) {
        ResponseMessage responseMessage = new ResponseMessage(bre.getMessage());
        return ResponseEntity.badRequest().body(responseMessage);
    }

    @ExceptionHandler(DataDuplicationException.class)
    public ResponseEntity<ResponseMessage> handleDataDuplicationException (HttpServletRequest request, DataDuplicationException dde) {
        ResponseMessage responseMessage = new ResponseMessage(dde.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMessage);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleDataNotFoundException (HttpServletRequest request, DataNotFoundException dnfe) {
        ResponseMessage responseMessage = new ResponseMessage(dnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ResponseMessage> handleInternalServerException (HttpServletRequest request, InternalServerException ise) {
        ResponseMessage responseMessage = new ResponseMessage(ise.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
    }
}
