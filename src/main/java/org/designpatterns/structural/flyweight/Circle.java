package org.designpatterns.structural.flyweight;

public class Circle implements Shape{
    private final String color; // intrinsic

    Circle(String color) {
        this.color = color;
    }

    public void draw(int x, int y) {
        System.out.println("Drawing " + color +
                " circle at (" + x + ", " + y + ")");
    }
}
