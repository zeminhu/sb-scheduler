package com.hzsolution;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Scheduler API", version = "1.0", description = "Scheduler Operation"))
public class SchedulerApplication {
    // access by http://localhost:8081/swagger-ui.html
    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }
}
