package org.designpatterns.structural.bridge.with;

public class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    void draw() {
        System.out.print("Drawing Circle in ");
        color.applyColor();
    }
}

class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    void draw() {
        System.out.print("Drawing Square in ");
        color.applyColor();
    }
}

