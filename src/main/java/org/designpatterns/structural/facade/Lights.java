package org.designpatterns.structural.facade;

public class Lights {
    public void on(){
        System.out.println("Light is ON");
    }

    public void off(){
        System.out.println("Light is OFF");
    }

    public void dim(int level){
        System.out.println("Dim to light "+level);
    }

}
