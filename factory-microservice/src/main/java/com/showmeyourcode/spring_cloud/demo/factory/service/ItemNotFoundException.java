package com.showmeyourcode.spring_cloud.demo.factory.service;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message) {
        super(message);
    }
}
