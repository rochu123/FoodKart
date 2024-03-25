package org.example;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Data
public class OrderDetails {
    private String orderId;
    private String user;
    String restaurantName;
    private float totalPrice;
    private OrderStatus orderStatus;



    public enum OrderStatus{
        IN_PROGRESS,
        DELIVERED
    }


    public static OrderDetailsBuilder builder() {
        return new OrderDetailsBuilder()
                .orderId(UUID.randomUUID().toString());
    }

    @Builder
    @Data
    public static class RestaurantPriceDetails {
        private String restaurantName;
        private float price;
        private int qty;
    }
}
