package org.example;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Map;

@Builder
@Data
public class Order {
    @NonNull
    private String id;
    private Map<String, Integer> orderDetails;//fooditem-qty
    private String user;

}
