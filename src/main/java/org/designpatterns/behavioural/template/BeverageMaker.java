package org.designpatterns.behavioural.template;

public abstract class BeverageMaker {
        // Template method defining the overall process
        void makeBeverage() {
        this.boilWater();
        this.brew();
        this.pourInCup();
        this.addCondiments();
        }

// Abstract methods to be implemented by subclasses
abstract void brew();
abstract void addCondiments();

        // Common methods
        void boilWater() {
        System.out.println("Boiling water");
        }

        void pourInCup() {
        System.out.println("Pouring into cup");
        }
}
