package org.designpatterns.structural.proxy;

public class RealInternet implements Internet{

    @Override
    public void connectsTo(String serverHost) {
        System.out.println("Connecting to :"+ serverHost);
    }
}
