package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AssetLifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDate;

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getEventDescription() { return eventDescription; }
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    // compatibility method
    public void setLoggedAt(LocalDateTime time) {
        this.eventDate = time;
    }
}
