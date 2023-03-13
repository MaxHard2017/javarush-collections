package com.gmail.maxhard001.javarush.collections.level2.task3212.service.impl;

import com.gmail.maxhard001.javarush.collections.level2.task3212.service.Service;

public class JMSServiceImpl implements Service {

    @Override
    public void execute() {
        System.out.println("Executing the JMSService");
    }

    @Override
    public String getName() {
        return "JMSService";
    }

}
