package org.designpatterns.structural.decorator;

public class CheeseTopping extends PizzaDecorator{


    public CheeseTopping(BasePizza basePizza) {
        super(basePizza);
    }

    @Override
    public String getDescription() {
        return basePizza.getDescription() +  " Extra Cheese";
    }

    @Override
    public double getCost() {
        return basePizza.getCost() + 100.0;
    }
}
