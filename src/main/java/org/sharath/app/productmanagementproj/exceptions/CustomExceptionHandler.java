package org.sharath.app.productmanagementproj.exceptions;

import org.sharath.app.productmanagementproj.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Something went wrong. Please try again");

//        return new ResponseEntity<>(errorDto,
//                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorDto,
                HttpStatusCode.valueOf(404));

    }
}
