package com.hzsolution.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzsolution.scheduler.ScheduledServiceImpl;
import com.hzsolution.scheduler.Scheduler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/scheduler")
public class SchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerController.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduledServiceImpl scheduledService;

    @Operation(summary = "Get a metadata dataset by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful execution of scheduled task",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SchedulerResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SchedulerResponse.class)) })})

    @GetMapping( path = "/reset", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity reset() throws Exception {
        logger.info("reset scheduler");
        ObjectMapper objectMapper = new ObjectMapper();
        String responseJSON;
        try {
            scheduler.runScheduledTask();
            SchedulerResponse response = new SchedulerResponse("complete", "none");
            responseJSON = objectMapper.writeValueAsString(response);
            return new ResponseEntity<>(responseJSON, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error to respond");
            SchedulerResponse response = new SchedulerResponse("error", "Internal Server Error");
            responseJSON = objectMapper.writeValueAsString(response);
            return new ResponseEntity<>(responseJSON, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
