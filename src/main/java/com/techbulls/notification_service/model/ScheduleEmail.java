package com.techbulls.notification_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  
    private Long id;

    @Column(name = "recipient")  
    private String recipient;

    @Column(name = "subject")    
    private String subject;

    @Column(name = "body")       
    private String body;

    @Column(name = "frequency")  
    private String frequency;

    @Column(name = "day_of_month")  
    private Integer dayOfMonth;

    @Column(name = "month")         
    private Integer month;

    @Column(name = "time")          
    private String time;

    @Column(name = "is_active")
    private boolean active = true;  // Default to true

}