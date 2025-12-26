package org.designpatterns.structural.bridge.with;

public class RedColor implements Color {
    public void applyColor() {
        System.out.println("Red Color");
    }
}

class BlueColor implements Color {
    public void applyColor() {
        System.out.println("Blue Color");
    }
}

