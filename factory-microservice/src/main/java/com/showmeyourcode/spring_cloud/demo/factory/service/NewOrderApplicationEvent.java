package com.showmeyourcode.spring_cloud.demo.factory.service;

import com.showmeyourcode.spring_cloud.demo.factory.model.Order;
import org.springframework.context.ApplicationEvent;

public class NewOrderApplicationEvent extends ApplicationEvent {

    public NewOrderApplicationEvent(Order source) {
        super(source);
    }
}
