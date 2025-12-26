package org.designpatterns.structural.bridge.with;


abstract class Shape {
        protected Color color;

        protected Shape(Color color) {
            this.color = color;
        }

        abstract void draw();
}


