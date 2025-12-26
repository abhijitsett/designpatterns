package org.designpatterns.creational.factory;

public class NotificationFactory {

    public static Notification getNotification(String type) {
        return switch (type) {
            case "EMAIL" -> new EmailNotification();
            case "SMS" -> new SMSNotification();
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }
}
