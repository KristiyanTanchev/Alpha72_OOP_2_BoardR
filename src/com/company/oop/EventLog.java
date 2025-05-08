package com.company.oop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLog {
    private final String description;
    private final LocalDateTime timestamp;

    public EventLog(String description) {
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public EventLog(){
        throw new IllegalArgumentException("Description cannot be empty");
    }

    public String getDescription() {
        return description;
    }

    public String viewInfo(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
        return String.format("[%s] %s", timestamp.format(format), description);
    }
}
