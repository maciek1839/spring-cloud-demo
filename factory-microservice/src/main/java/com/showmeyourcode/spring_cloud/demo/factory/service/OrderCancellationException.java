package com.showmeyourcode.spring_cloud.demo.factory.service;

public class OrderCancellationException extends RuntimeException {

    public OrderCancellationException(String message) {
        super(message);
    }
}
