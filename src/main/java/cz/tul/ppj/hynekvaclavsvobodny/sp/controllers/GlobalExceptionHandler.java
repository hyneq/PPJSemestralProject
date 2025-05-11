package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e, WebRequest request) {
        // TODO log
        return ResponseEntity.internalServerError().body("Server error, see the log if you have access");
    }
}