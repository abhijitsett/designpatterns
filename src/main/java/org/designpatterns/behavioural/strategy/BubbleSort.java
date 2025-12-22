package org.designpatterns.behavioural.strategy;

public class BubbleSort implements SortingStrategy{

    @Override
    public void sort(String[] arr) {
        System.out.println("Bubble sort");
    }
}
