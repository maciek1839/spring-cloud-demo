package com.showmeyourcode.spring_cloud.demo.factory.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultIdProvider implements IdProvider{
    @Override
    public UUID generateId() {
        return UUID.randomUUID();
    }
}
