package org.designpatterns.structural.bridge.with;

public class BridgeDemo {
    public static void main(String[] args) {

        Shape s1 = new Circle(new RedColor());
        Shape s2 = new Square(new BlueColor());

        s1.draw();
        s2.draw();
    }
}
