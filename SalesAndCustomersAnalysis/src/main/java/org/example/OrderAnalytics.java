package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderAnalytics {

    public static Set<String> getUniqueCities(List<Order> orders){
        return orders.stream()
                .map(order -> order.getCustomer().getCity())
                .filter(city -> city != null && !city.isBlank())
                .collect(Collectors.toSet());
    }

    public static double getTotalIncomeForCompletedOrders(List<Order> orders){
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public static String getMostPopularProduct(List<Order> orders){
        return orders.stream()
                .flatMap(order ->  order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.summingInt(OrderItem::getQuantity)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static double getAverageCheckForDeliveredOrders(List<Order> orders){
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(order -> order.getItems().stream()
                        .mapToDouble(item -> item.getPrice() * item.getQuantity())
                        .sum())
                .average()
                .orElse(0.0);
    }

    public static List<Customer> getCustomersWithMoreThanFiveOrders(List<Order> orders){
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 5)
                .map(Map.Entry::getKey)
                .toList();
    }
}
