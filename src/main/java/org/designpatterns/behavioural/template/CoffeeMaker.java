package org.designpatterns.behavioural.template;

public class CoffeeMaker extends BeverageMaker{

    @Override
    void brew() {
        System.out.println("Dipping coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
