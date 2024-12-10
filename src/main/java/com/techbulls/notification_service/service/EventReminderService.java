package com.techbulls.notification_service.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.techbulls.notification_service.model.EventReminder;
import com.techbulls.notification_service.repository.EventReminderRepository;

@Service
public class EventReminderService {

    @Autowired
    private EventReminderRepository eventReminderRepository;

    @Autowired
    private EmailService emailService;

    // @Scheduled(cron = "*/30 * * * * *") // Runs every 30 seconds
    @Scheduled(cron = "0 0 10 * * *") // This cron runs every day at 10 AM
    public void processEventReminders() {
        LocalDate today = LocalDate.now();

        // Get active reminders that should trigger today
        List<EventReminder> remindersToSend = eventReminderRepository.findActiveRemindersForDate(today);

        if (!remindersToSend.isEmpty()) {
            for (EventReminder reminder : remindersToSend) {
                sendReminder(reminder);
            }
        }
        else
        {
            System.out.println("No active reminders");
        }
    }

    private void sendReminder(EventReminder reminder) {
        // Send the email using the EmailService
        emailService.sendEmail(reminder.getRecipientEmail(), reminder.getReminderSubject(), reminder.getReminderBody());
        System.out.println("Reminder sent for event: " + reminder.getEventName());
    }

    // Method to create or update event reminders
    public EventReminder saveEventReminder(EventReminder reminder) {
        return eventReminderRepository.save(reminder);
    }
}
