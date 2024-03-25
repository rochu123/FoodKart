package org.example;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class Restaurant {
    private Map<String, Float> menu;
    private String name;
    private String location;

    private int totalCapacity;
    private int availableCapacity;
    private double rating;

    public void bookCapacity(){
        this.availableCapacity--;
    }

    public void freeCapacity(){
        this.availableCapacity++;
    }

    public void updateMenu(Map<String, Float> menuUpdate) {
        for (Map.Entry<String, Float> entry : menuUpdate.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            menu.put(key, value);
        }
    }

    public void add(String item, Float price) {
        menu.put(item, price);
    }

    public void update(String item, Float price) {
        add(item, price);
    }

    public void printMenu() {
        for (Map.Entry<String, Float> entry : menu.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            System.out.println("Item is " + key + "price is " + value);
        }
    }
}
