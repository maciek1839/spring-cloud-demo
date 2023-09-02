package com.showmeyourcode.spring_cloud.demo.factory.api;

import com.showmeyourcode.spring_cloud.demo.factory.service.OrderCancellationException;
import com.showmeyourcode.spring_cloud.demo.factory.service.OrderNotFoundException;
import com.showmeyourcode.spring_cloud.demo.factory.service.ItemNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderCancellationException.class)
    @ResponseStatus(HttpStatus.GONE)
    public String orderCancellationExceptionHandler(Exception ex) {
        log.error("Caught an exception ({}): ", ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler({OrderNotFoundException.class, ItemNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String orderNotFoundExceptionHandler(Exception ex) {
        log.error("Caught an exception ({}): ", ex.getMessage(), ex);
        return ex.getMessage();
    }
}
