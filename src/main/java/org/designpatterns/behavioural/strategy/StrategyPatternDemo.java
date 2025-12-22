package org.designpatterns.behavioural.strategy;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        SortingContext sortingContext = new SortingContext(new BubbleSort());
        String[] arr = {"Java","Python","JavaScript","Ruby"};
        sortingContext.performSort(arr);
    }
}
