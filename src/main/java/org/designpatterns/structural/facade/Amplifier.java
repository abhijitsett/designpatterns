package org.designpatterns.structural.facade;

public class Amplifier {
    public void on(){
        System.out.println("Amplifier is ON");
    }

    public void off(){
        System.out.println("Amplifier is OFF");
    }

    public void setVolume(int vol){
        System.out.println("Amplifier volume is "+vol);
    }
}
