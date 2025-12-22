package org.designpatterns.behavioural.state;

public class StatePatternDemo {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.pressButton();
        vm.setState(new DispenseState());
        vm.pressButton();
    }
}
