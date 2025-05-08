package com.company.oop;

public enum Status {
    OPEN,
    TODO,
    IN_PROGRESS,
    DONE,
    VERIFIED;

    @Override
    public String toString() {
        switch (this) {
            case OPEN:
                return "Open";
            case TODO:
                return "ToDo";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";
            case VERIFIED:
                return "Verified";
        }
        return "UNKNOWN";
    }
}
