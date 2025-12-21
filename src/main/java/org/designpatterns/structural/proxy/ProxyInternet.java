package org.designpatterns.structural.proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.prefs.PreferenceChangeListener;

public class ProxyInternet implements Internet{
    Internet internet;
    List<String> blockedSites = Arrays.asList("facebook.com","twitter.com");

    public ProxyInternet(){
        this.internet = new RealInternet();
    }

    @Override
    public void connectsTo(String serverHost) throws Exception {
        if(blockedSites.contains(serverHost.toLowerCase())){
            throw new Exception("Access to : "+ serverHost + " is blocked");
        }
        internet.connectsTo(serverHost);
    }


}
