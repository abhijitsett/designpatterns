package org.designpatterns.behavioural.strategy;

public class SortingContext {
    SortingStrategy sortingStrategy;

    public SortingContext(SortingStrategy sortingStrategy){
        this.sortingStrategy = sortingStrategy;
    }

    public void performSort(String[] arr){
        sortingStrategy.sort(arr);
    }

    public void setSortingStrategy(SortingStrategy sortingStrategy){
        this.sortingStrategy = sortingStrategy;
    }
}
