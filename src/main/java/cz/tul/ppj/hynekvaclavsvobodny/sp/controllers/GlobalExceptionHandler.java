package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e, HttpServletRequest request) throws Exception {
        logger.error("An exception occurred during request [{} {}] with query [{}]",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString(),
                e);

        throw e;
    }
}