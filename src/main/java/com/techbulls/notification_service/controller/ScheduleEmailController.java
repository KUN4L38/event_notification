package com.techbulls.notification_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techbulls.notification_service.model.ScheduleEmail;
import com.techbulls.notification_service.service.ScheduleEmailService;

@RestController
@RequestMapping("/schedule-email")
public class ScheduleEmailController {

    @Autowired
    private ScheduleEmailService scheduleEmailService;

    @PostMapping
    public ResponseEntity<ScheduleEmail> createScheduleEmail(@RequestBody ScheduleEmail scheduleEmail) {
        
        // If frequency is not valid, return BAD_REQUEST
        if (scheduleEmail.getFrequency() == null || 
            (!scheduleEmail.getFrequency().equals("MONTHLY") && !scheduleEmail.getFrequency().equals("YEARLY"))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Save the scheduled email
        ScheduleEmail savedScheduleEmail = scheduleEmailService.saveScheduledEmail(scheduleEmail);
        return new ResponseEntity<>(savedScheduleEmail, HttpStatus.CREATED);
    }
}