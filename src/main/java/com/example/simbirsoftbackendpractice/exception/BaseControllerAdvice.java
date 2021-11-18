package com.example.simbirsoftbackendpractice.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class BaseControllerAdvice {

    private final DateTimeFormatter dateTimeFormatter;

    @ExceptionHandler(NoRightException.class)
    public Object noRightException(NoRightException ex) {
        return response(HttpStatus.METHOD_NOT_ALLOWED, ex);
    }

    private Object response(HttpStatus status, Exception ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", status.toString());
        body.put("timestamp", dateTimeFormatter.format(ZonedDateTime.now()));
        return new ResponseEntity<>(body, headers, status);
    }
}
