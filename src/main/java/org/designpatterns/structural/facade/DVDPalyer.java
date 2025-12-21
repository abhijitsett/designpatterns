package org.designpatterns.structural.facade;

public class DVDPalyer {
    public void on(){
        System.out.println("DVD Player is ON");
    }

    public void play(String movie){
        System.out.println("DVD Player is playing movie "+movie);
    }

    public void stop(){
        System.out.println("DVD Player is STOP");
    }

    public void eject(){
        System.out.println("DVD Player is EJECT");
    }

    public void off(){
        System.out.println("DVD Player is OFF");
    }
}
