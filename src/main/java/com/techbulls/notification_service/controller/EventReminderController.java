package com.techbulls.notification_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techbulls.notification_service.model.EventReminder;
import com.techbulls.notification_service.service.EventReminderService;

@RestController
@RequestMapping("/event-reminder")
public class EventReminderController {

    @Autowired
    private EventReminderService eventReminderService;

    @PostMapping
    public ResponseEntity<EventReminder> createEventReminder(@RequestBody EventReminder eventReminder) {
        EventReminder savedEventReminder = eventReminderService.saveEventReminder(eventReminder);
        return new ResponseEntity<>(savedEventReminder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventReminder> updateEventReminder(@PathVariable Long id, @RequestBody EventReminder eventReminder) {
        eventReminder.setId(id);
        EventReminder updatedEventReminder = eventReminderService.saveEventReminder(eventReminder);
        return new ResponseEntity<>(updatedEventReminder, HttpStatus.OK);
    }
}