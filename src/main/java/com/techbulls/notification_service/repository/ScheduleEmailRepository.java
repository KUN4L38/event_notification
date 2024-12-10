package com.techbulls.notification_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techbulls.notification_service.model.ScheduleEmail;

@Repository
public interface ScheduleEmailRepository extends JpaRepository<ScheduleEmail, Long> {

@Query("SELECT se FROM ScheduleEmail se " +
    "WHERE se.active = true " +
    "AND se.time = :currentTime " +
    "AND CURRENT_DATE = TO_DATE(:currentDate, 'YYYY-MM-DD')")
List<ScheduleEmail> findEmailsToSend(@Param("currentDate") String currentDate, @Param("currentTime") String currentTime);


}