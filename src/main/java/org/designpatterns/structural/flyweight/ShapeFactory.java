package org.designpatterns.structural.flyweight;

import java.util.*;

public class ShapeFactory {
    private static final Map<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color){
        return circleMap.computeIfAbsent(color, Circle::new);
    }
}
