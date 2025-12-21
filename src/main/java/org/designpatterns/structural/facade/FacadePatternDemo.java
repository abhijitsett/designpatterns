package org.designpatterns.structural.facade;

public class FacadePatternDemo {
    public static void main(String[] args) {
        DVDPalyer dvdPalyer = new DVDPalyer();
        Amplifier amplifier = new Amplifier();
        Speakers speakers = new Speakers();
        Lights lights = new Lights();
        Projector projector = new Projector();

        HomeTheaterFacadeLayer homeTheaterFacadeLayer = new HomeTheaterFacadeLayer(dvdPalyer,amplifier,projector,speakers,lights);
        homeTheaterFacadeLayer.watchMovie("Dhoom 3");
        homeTheaterFacadeLayer.endMovie("Dhoom 3");
    }
}
