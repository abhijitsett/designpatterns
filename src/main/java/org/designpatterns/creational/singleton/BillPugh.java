package org.designpatterns.creational.singleton;

public class BillPugh {

    private BillPugh(){}

    private static class Holder{
        private static final BillPugh INSTANCE = new BillPugh();
    }

    public static BillPugh getInstance(){
        return Holder.INSTANCE;
    }
}
