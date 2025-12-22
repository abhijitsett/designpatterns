package org.designpatterns.behavioural.state;

import java.util.IdentityHashMap;

public class VendingMachine {

    private State state;

    public VendingMachine(){
        state = new IdleState();
    }

    void setState(State state){
        this.state = state;
    }

    void pressButton(){
        state.pressButton(this);
    }
}
