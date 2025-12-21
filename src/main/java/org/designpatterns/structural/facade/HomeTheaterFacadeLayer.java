package org.designpatterns.structural.facade;

public class HomeTheaterFacadeLayer {
    private DVDPalyer dvdPalyer;
    private Amplifier amplifier;
    private Projector projector;
    private Speakers speakers;
    private Lights lights;

    public HomeTheaterFacadeLayer(DVDPalyer dvdPalyer, Amplifier amplifier, Projector projector, Speakers speakers, Lights lights) {
        this.dvdPalyer = dvdPalyer;
        this.amplifier = amplifier;
        this.projector = projector;
        this.speakers = speakers;
        this.lights = lights;
    }

    public void watchMovie(String movieName){
        System.out.println("Movie name "+movieName);
        lights.dim(15);
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setVolume(50);
        speakers.on();
        dvdPalyer.on();
        dvdPalyer.play(movieName);
    }

    public void endMovie(String movieName){
        System.out.println("Stopping movie "+movieName);
        lights.on();
        projector.off();
        amplifier.off();
        speakers.off();
        dvdPalyer.eject();
        dvdPalyer.off();
    }



}
