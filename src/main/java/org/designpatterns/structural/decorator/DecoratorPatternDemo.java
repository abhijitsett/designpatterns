package org.designpatterns.structural.decorator;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        BasePizza pizza = new PlainPizza();
        System.out.println("Order 1 :"+pizza.getDescription()+" "+pizza.getCost());

        BasePizza pizza1 = new CheeseTopping(pizza);
        System.out.println("Order 2 : "+pizza1.getDescription()+" "+pizza1.getCost());
    }
}
