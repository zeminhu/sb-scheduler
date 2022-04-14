package com.hzsolution;

import org.springframework.stereotype.Service;

@Service
public class ScheduledServiceImpl implements ScheduledService {

    @Override
    public void processData() {
        System.out.println("dummy run");
    }
}
