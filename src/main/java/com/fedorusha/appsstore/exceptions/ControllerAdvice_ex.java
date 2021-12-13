package com.fedorusha.appsstore.exceptions;

import com.fedorusha.appsstore.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvice_ex {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception e) {
        ResponseDto response = new ResponseDto(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
