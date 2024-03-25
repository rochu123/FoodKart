package org.example;

import java.util.List;
import java.util.Map;

public interface OrderManager {

//    OrderDetails bookOrder(Order order , Map<String, List<String>> itemRestaurantMap, Map<String, Restaurant> restaurantMap);
    OrderDetails bookOrder(Order order , List<Restaurant> restaurants);
    OrderDetails completeOrder(OrderDetails orderDetails, Map<String, Restaurant> restaurantMap);
}
