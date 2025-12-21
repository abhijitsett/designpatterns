package org.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Directory implements Component{
    String name;
    List<Component> components = new ArrayList<>();

    Directory(String name){
        this.name = name;
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public void removeComponent(Component component){
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory name :"+name);
        for(Component component : components){
            component.showDetails();
        }
    }
}
