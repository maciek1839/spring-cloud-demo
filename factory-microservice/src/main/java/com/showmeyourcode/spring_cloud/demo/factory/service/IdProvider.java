package com.showmeyourcode.spring_cloud.demo.factory.service;

import java.util.UUID;

public interface IdProvider {

    UUID generateId();
}
