package org.designpatterns.creational.abstract_factory;

public class GreenCircleFactory implements AbstractFactory{

    @Override
    public Color createColor() {
        return new Green();
    }

    @Override
    public Shape createShape() {
        return new Circle();
    }
}
