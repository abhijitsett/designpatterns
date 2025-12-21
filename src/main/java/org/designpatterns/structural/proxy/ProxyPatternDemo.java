package org.designpatterns.structural.proxy;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();

        try{
            internet.connectsTo("google.com");
            internet.connectsTo("facebook.com");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
