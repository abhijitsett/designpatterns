package org.designpatterns.creational.factory;

public class FactoryDemo {
    public static void main(String[] args) {
        Notification notification = NotificationFactory.getNotification("EMAIL");
        notification.notifyUser();
    }
}
