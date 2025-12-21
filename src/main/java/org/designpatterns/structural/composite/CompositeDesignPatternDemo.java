package org.designpatterns.structural.composite;

public class CompositeDesignPatternDemo {
    public static void main(String[] args) {
        Component file1 = new File("file1.txt");
        Component file2 = new File("file2.txt");

        Directory directory = new Directory("directory1");
        directory.addComponent(file1);
        directory.addComponent(file2);

        directory.showDetails();
    }
}
