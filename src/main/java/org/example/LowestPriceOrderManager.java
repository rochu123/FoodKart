package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.example.OrderDetails.OrderStatus.DELIVERED;
import static org.example.OrderDetails.OrderStatus.IN_PROGRESS;

public class LowestPriceOrderManager implements OrderManager {
    @Override
    public OrderDetails bookOrder(Order order, List<Restaurant> restaurants) {
        OrderDetails orderDetails = OrderDetails.builder().user(order.getUser()).orderStatus(IN_PROGRESS).orderId(order.getId()).build();
        List<String> items = new ArrayList<>(order.getOrderDetails().keySet());
        List<Restaurant> applicableRestaurants = filterAplicableRestaurants(items, restaurants);
//        pickRestaurantWithMinPrice(items, applicableRestaurants);

        Restaurant selectedRestaurant = null;
        float minPrice = Float.MAX_VALUE;
        for (Restaurant restaurant : applicableRestaurants) {
            float sum = 0;
            for (String item : items) {
                sum += restaurant.getMenu().get(item);
            }
            if (sum < minPrice) {
                minPrice = sum;
                selectedRestaurant = restaurant;
            }
        }

        if (Objects.isNull(selectedRestaurant)) {
            throw new RuntimeException("Order can't be fulfilled now");
        }

        selectedRestaurant.bookCapacity();
        orderDetails.setTotalPrice(minPrice);
        orderDetails.setRestaurantName(selectedRestaurant.getName());
        return orderDetails;
    }

    @Override
    public OrderDetails completeOrder(OrderDetails orderDetails, Map<String, Restaurant> restaurantMap) {
        orderDetails.setOrderStatus(DELIVERED);
        restaurantMap.get(orderDetails.getRestaurantName()).freeCapacity();
        System.out.println("Order has been dispatched");
        return orderDetails;
    }

    private Restaurant pickRestaurantWithMinPrice(List<String> items, List<Restaurant> restaurants) {
        Restaurant selectedRestaurant = null;
        float minPrice = Float.MAX_VALUE;
        for (Restaurant restaurant : restaurants) {
            float sum = 0;
            for (String item : items) {
                sum += restaurant.getMenu().get(item);
            }
            if (sum < minPrice) {
                minPrice = sum;
                selectedRestaurant = restaurant;
            }
        }
        return selectedRestaurant;
    }


    private List<Restaurant> filterAplicableRestaurants(List<String> items, List<Restaurant> restaurants) {
        List<Restaurant> applicableRestaurants = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            List<String> menu = new ArrayList<>(restaurant.getMenu().keySet());
            if (menu.containsAll(items) && restaurant.getAvailableCapacity() > 0) {
                applicableRestaurants.add(restaurant);
            }
        }
        return applicableRestaurants;
    }
}
