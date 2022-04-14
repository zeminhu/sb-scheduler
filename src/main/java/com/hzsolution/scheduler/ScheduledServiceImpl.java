package com.hzsolution.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScheduledServiceImpl implements ScheduledService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledServiceImpl.class);

    @Override
    public void processData() {
        logger.info("dummy run");
    }
}
