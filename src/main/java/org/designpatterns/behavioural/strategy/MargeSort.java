package org.designpatterns.behavioural.strategy;

public class MargeSort implements SortingStrategy{

    @Override
    public void sort(String[] arr) {
        System.out.println("Merge sort");
    }
}
