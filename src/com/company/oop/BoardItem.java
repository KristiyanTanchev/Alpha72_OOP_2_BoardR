package com.company.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BoardItem {
    private String title;
    private LocalDate dueDate;
    private Status status;
    private final ArrayList<EventLog> logList;

    BoardItem(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        status = Status.OPEN;
        this.logList = new ArrayList<>();
        addLog(title, dueDate);
    }
    private void addLog(String description){
        logList.add(new EventLog(String.format("%s", description)));
    }

    private void addLog(String oldValue, String newValue){
        logList.add(new EventLog(String.format("%s changed from %s to %s", "Title", oldValue, newValue)));
    }

    private void addLog(Status oldValue, Status newValue){
        logList.add(new EventLog(String.format("%s changed from %s to %s", "Status", oldValue, newValue)));
    }

    private void addLog(String description, LocalDate dueDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        logList.add(new EventLog(String.format("Item created: '%s', [Open | %s]",
                description, dueDate.format(formatter))));
    }

    public void displayHistory(){
        for (EventLog log: logList){
            System.out.println(log.viewInfo());
        }
    }

    public void setDueDate(LocalDate dueDate) {
        addLog(this.dueDate, dueDate);
        this.dueDate = dueDate;
    }

    private void addLog(LocalDate oldDate, LocalDate newDate) {
        logList.add(new EventLog(
                String.format("%s changed from %s to %s", "Due date", oldDate, newDate)));
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setTitle(String title) {
        if (title.length() < 5 || title.length() > 30){
            throw new IllegalArgumentException("Please provide a title with length between 5 and 30 chars");
        }
        addLog(this.title, title);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    void advanceStatus() {
        switch (status) {
            case OPEN:
                status = Status.TODO;
                addLog(Status.OPEN, this.status);
                break;
            case TODO:
                status = Status.IN_PROGRESS;
                addLog(Status.TODO, this.status);
                break;
            case IN_PROGRESS:
                status = Status.DONE;
                addLog(Status.IN_PROGRESS, this.status);
                break;
            case DONE:
                status = Status.VERIFIED;
                addLog(Status.DONE, this.status);
                break;
            case VERIFIED:
                addLog("Can't advance, already at Verified");
                break;
        }
    }

    void revertStatus() {
        switch (status) {
            case VERIFIED:
                status = Status.DONE;
                addLog(Status.VERIFIED, this.status);
                break;
            case DONE:
                status = Status.IN_PROGRESS;
                addLog(Status.DONE, this.status);
                break;
            case IN_PROGRESS:
                status = Status.TODO;
                addLog(Status.IN_PROGRESS, this.status);
                break;
            case TODO:
                status = Status.OPEN;
                addLog(Status.TODO, this.status);
                break;
            case OPEN:
                addLog("Can't revert, already at Open");
        }
    }

    String viewInfo() {
        return String.format("'%s', [%s | %s]", title, status, dueDate);
    }
}
