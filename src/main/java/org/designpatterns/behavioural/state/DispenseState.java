package org.designpatterns.behavioural.state;

import java.util.IdentityHashMap;

public class DispenseState implements State{

    @Override
    public void pressButton(VendingMachine vm) {
        System.out.println("Dispensing state");
        vm.setState(new IdleState());
    }
}
