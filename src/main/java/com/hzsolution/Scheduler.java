package com.hzsolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    ScheduledServiceImpl scheduledService;
    @Autowired
    private Environment env;

    boolean scheduleEnabled;
    int scheduledRate;

    @PostConstruct
    public void init() {
        // TODO get value from configuration: database or configuration file
        scheduleEnabled = Boolean.parseBoolean(env.getProperty("spring.schedulerEnable"));
        scheduledRate = Integer.parseInt(env.getProperty("spring.scheduledRate"));
        logger.info("Scheduler scheduleEnabled: " + scheduleEnabled);
        logger.info("Scheduler scheduledRate for every " + scheduledRate/60000 + " minute(s)");
    }

    @Scheduled(fixedRateString = "${spring.scheduledRate}")
    private void runScheduledTask() {
        if(scheduleEnabled) {
            logger.info("start runScheduledTask at fixed rate for every " + scheduledRate/60000 + " minute(s)");
            scheduledService.processData();
            logger.info("complete runScheduledTask ");
        }
    }
}
