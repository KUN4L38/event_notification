package com.techbulls.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techbulls.notification_service.model.EventReminder;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventReminderRepository extends JpaRepository<EventReminder, Long> {
    
    @Query("SELECT er FROM EventReminder er " +
           "WHERE er.active = true " +
           "AND er.eventDate = :eventDate")
    List<EventReminder> findActiveRemindersForDate(LocalDate eventDate);
}