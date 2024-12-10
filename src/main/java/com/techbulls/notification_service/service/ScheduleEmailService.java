package com.techbulls.notification_service.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.techbulls.notification_service.model.ScheduleEmail;
import com.techbulls.notification_service.repository.ScheduleEmailRepository;

@Service
public class ScheduleEmailService {

    @Autowired
    private ScheduleEmailRepository scheduleEmailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    // Method to save the scheduled email
    public ScheduleEmail saveScheduledEmail(ScheduleEmail scheduleEmail) {
        if (scheduleEmail.getFrequency().equals("MONTHLY") || scheduleEmail.getFrequency().equals("YEARLY")) {
            return scheduleEmailRepository.save(scheduleEmail);
        }
        return null;
    }

    // Scheduled task to check for emails to send
    @Scheduled(cron = "*/30 * * * * *") // Runs every 30 seconds
    public void checkAndSendScheduledEmails() {
        System.out.println("Scheduled task invoked at: " + LocalDateTime.now());

        // Get current date and time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0); // Remove seconds and nanoseconds precision

        // Fetch emails to send
        List<ScheduleEmail> emailsToSend = scheduleEmailRepository.findEmailsToSend(currentDate.toString(), currentTime.toString());

        if(!emailsToSend.isEmpty())
        {
        // Process each email
        for (ScheduleEmail email : emailsToSend) {
            sendEmail(email);
        }
        }
        else{
            System.out.println("No emails to send.");
        }
    }

    // Method to send the email
    private void sendEmail(ScheduleEmail scheduleEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(scheduleEmail.getRecipient());
        message.setSubject(scheduleEmail.getSubject());
        message.setText(scheduleEmail.getBody());
        javaMailSender.send(message);
        System.out.println("Email sent to: " + scheduleEmail.getRecipient());
    }
}