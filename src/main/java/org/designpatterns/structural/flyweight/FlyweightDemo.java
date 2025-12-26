package org.designpatterns.structural.flyweight;

import org.designpatterns.structural.flyweight.ShapeFactory;

public class FlyweightDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Shape circle = ShapeFactory.getCircle("Red");
            circle.draw(i * 10, i * 20);
        }
    }
}
