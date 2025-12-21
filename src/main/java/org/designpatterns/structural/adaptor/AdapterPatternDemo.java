package org.designpatterns.structural.adaptor;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3","mp3file");
        audioPlayer.play("vlc","vlcfile");

    }
}
