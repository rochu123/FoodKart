package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        FoodKart foodKart = FoodKart.builder().restaurants(new ArrayList<>()).orderManager(new LowestPriceOrderManager()).orders(new ArrayList<>()).restaurantMap(new HashMap<>()).build();


        Restaurant res1 = Restaurant.builder().name("res1").totalCapacity(1).availableCapacity(1).menu(new HashMap<>()).build();
        Restaurant res2 = Restaurant.builder().name("res2").totalCapacity(0).availableCapacity(0).menu(new HashMap<>()).build();
        Restaurant res3 = Restaurant.builder().name("res3").totalCapacity(5).availableCapacity(5).menu(new HashMap<>()).build();

        res1.add("item1", 20f);
        res1.add("item2", 30f);
        res1.add("item4", 40f);

        res2.add("item1", 30f);
        res2.add("item3", 10f);
        res2.add("item5", 20f);

        res3.add("item2", 50f);
        res3.add("item3", 20f);
        res3.add("item4", 60f);
        foodKart.onBoardRestaurant(res1);
        foodKart.onBoardRestaurant(res2);
        foodKart.onBoardRestaurant(res3);
//        foodKart.completeOrder("1");
        try {
            foodKart.onBoardRestaurant(res3);
        } catch (Exception e) {
            System.out.println("Exception while adding Restaurant " + e.getMessage());
        }


        foodKart.updatePriceOfItem("res1","item1",35f);

        foodKart.printMenuForRestaurant("res1");



        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("item1",1);
        Order order = Order.builder().orderDetails(orderMap).user("rohit").id("1234").build();
        OrderDetails orderDetails = foodKart.bookOrder(order);

        System.out.println("order details "+ orderDetails.toString());
        System.out.println("res1 details "+ res1.toString());
        foodKart.completeOrder(order.getId());
        System.out.println("order details "+ orderDetails.toString());
        System.out.println("res1 details "+ res1.toString());

    }
}