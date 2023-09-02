package com.showmeyourcode.spring_cloud.demo.factory.service;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }
}
