package org.example;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

@Builder
@Data
public class FoodKart {
    Map<String, Restaurant> restaurantMap;
//    Map<String, List<String>> itemRestaurantMap;
    private List<Restaurant> restaurants;
    private OrderManager orderManager;
    private List<OrderDetails> orders;

    public void onBoardRestaurant(Restaurant restaurant) {
        System.out.println("adding res " + restaurant.toString());
        validateRestaurant(restaurant);
        restaurants.add(restaurant);
        restaurantMap.put(restaurant.getName(), restaurant);
//        updateItemRestaurantMap(restaurant);
    }

    public void updatePriceOfItem(String restaurantName, String item, float price) {
        Restaurant restaurant = fetchRestaurant(restaurantName);
        restaurant.update(item, price);
    }

    public void printMenuForRestaurant(String restaurantName) {
        Restaurant restaurant = fetchRestaurant(restaurantName);
        restaurant.printMenu();
    }

    public OrderDetails bookOrder(Order order) {
        OrderDetails orderDetails = orderManager.bookOrder(order, restaurants);
        orders.add(orderDetails);
        return orderDetails;
    }

    public OrderDetails completeOrder(String orderId) {
        OrderDetails selectedOrder = orders.stream().filter(order -> Objects.equals(order.getOrderId(), orderId)).findFirst().orElse(null);
        if (isNull(selectedOrder)) throw new RuntimeException("invalid order id");
        return orderManager.completeOrder(selectedOrder,restaurantMap);
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (restaurantMap.containsKey(restaurant.getName())) {
            throw new RuntimeException("Restaurant with name already exists " + restaurant.getName());
        }
    }


    private Restaurant fetchRestaurant(String restaurantName) {
        Restaurant restaurant = restaurantMap.get(restaurantName);
        if (isNull(restaurant)) {
            throw new RuntimeException("restaurantName does not exist : " + restaurantName);
        }
        return restaurant;
    }

//    private void updateItemRestaurantMap(Restaurant restaurant) {
//        for (String item : restaurant.getMenu().keySet()) {
//            List<String> restaurants = itemRestaurantMap.getOrDefault(item, new ArrayList<>());
//            restaurants.add(restaurant.getName());
//            itemRestaurantMap.put(item, restaurants);
//        }
//    }

}
