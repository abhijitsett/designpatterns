package org.designpatterns.creational.abstract_factory;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        AbstractFactory factory = new GreenCircleFactory();

        Color color = factory.createColor();
        Shape shape = factory.createShape();

        color.fillColor();
        shape.draw();
    }
}
