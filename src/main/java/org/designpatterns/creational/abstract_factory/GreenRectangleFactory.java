package org.designpatterns.creational.abstract_factory;

public class GreenRectangleFactory implements AbstractFactory{

    @Override
    public Color createColor() {
        return new Green();
    }

    @Override
    public Shape createShape() {
        return new Rectangle();
    }
}
