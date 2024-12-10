package com.techbulls.notification_service.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_reminder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name", nullable = false)
    private String eventName; // e.g., "NDA Expiration"

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate; // e.g., 2024-12-31

    @Column(name = "reminder_days_prior", nullable = false)
    private int reminderDaysPrior; // e.g., 10 days before

    @Column(name = "recipient_email", nullable = false)
    private String recipientEmail;

    @Column(name = "reminder_subject", nullable = false)
    private String reminderSubject;

    @Column(name = "reminder_body", nullable = false, columnDefinition = "TEXT")
    private String reminderBody;

    @Column(name = "is_active", nullable = false)
    private boolean active;
}