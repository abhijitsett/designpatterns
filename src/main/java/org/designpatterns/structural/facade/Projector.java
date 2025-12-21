package org.designpatterns.structural.facade;

public class Projector {
    public void on(){
        System.out.println("Projector is ON");
    }

    public void wideScreenMode(){
        System.out.println("Projector is widescreen mode");
    }

    public void off(){
        System.out.println("Projector is OFF");
    }
}
