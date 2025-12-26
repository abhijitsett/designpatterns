package org.designpatterns.creational.abstract_factory;

public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}
