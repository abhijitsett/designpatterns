package org.designpatterns.behavioural.state;

public class IdleState implements State{
    @Override
    public void pressButton(VendingMachine vm) {
        System.out.println("Coin inserted");
        vm.setState(new DispenseState());
    }
}
