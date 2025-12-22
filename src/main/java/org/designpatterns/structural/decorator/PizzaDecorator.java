package org.designpatterns.structural.decorator;

public abstract class PizzaDecorator implements BasePizza{
    BasePizza basePizza;
    public PizzaDecorator(BasePizza basePizza){
        this.basePizza = basePizza;
    }
}
