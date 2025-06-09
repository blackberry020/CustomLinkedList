

import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderAnalyticsTest {

    private List<Order> orders;
    private Customer customer1, customer2, customer3;

    @BeforeEach
    public void setup(){
        customer1 = new Customer("C1", "Alice", "alice@example.com", LocalDateTime.now().minusYears(1), 30, "Minsk");
        customer2 = new Customer("C2", "Bob", "bob@example.com", LocalDateTime.now().minusMonths(3), 25, "Brest");
        customer3 = new Customer("C3", "Charlie", "charlie@example.com", LocalDateTime.now().minusDays(10), 40, "Minsk");

        OrderItem item1 = new OrderItem("Phone", 2, 300.0, Category.ELECTRONICS);
        OrderItem item2 = new OrderItem("T-shirt", 3, 20.0, Category.CLOTHING);
        OrderItem item3 = new OrderItem("Book", 1, 15.0, Category.BOOKS);

        orders = List.of(
                new Order("O1", LocalDateTime.now(), customer1, List.of(item1), OrderStatus.DELIVERED),
                new Order("O2", LocalDateTime.now(), customer2, List.of(item2), OrderStatus.PROCESSING),
                new Order("O3", LocalDateTime.now(), customer1, List.of(item3), OrderStatus.DELIVERED),
                new Order("O4", LocalDateTime.now(), customer1, List.of(item3), OrderStatus.DELIVERED),
                new Order("O5", LocalDateTime.now(), customer3, List.of(item1, item3), OrderStatus.CANCELLED),
                new Order("O6", LocalDateTime.now(), customer1, List.of(item3), OrderStatus.DELIVERED),
                new Order("O7", LocalDateTime.now(), customer1, List.of(item2), OrderStatus.DELIVERED),
                new Order("O8", LocalDateTime.now(), customer1, List.of(item2), OrderStatus.DELIVERED)
        );
    }


    @Test
    public void testGetUniqueCities(){
        Set<String> cities = OrderAnalytics.getUniqueCities(orders);
        assertEquals(Set.of("Minsk", "Brest"), cities);
    }

    @Test
    public void testGetTotalIncomeForCompletedOrders(){
        double income = OrderAnalytics.getTotalIncomeForCompletedOrders(orders);
        assertEquals(765.0, income);
    }

    @Test
    public void testGetMostPopularProduct(){
        String popularProduct = OrderAnalytics.getMostPopularProduct(orders);
        assertEquals("T-shirt", popularProduct);
    }

    @Test
    public void testGetAverageCheckForDeliveredOrders(){
        double averageCheck = OrderAnalytics.getAverageCheckForDeliveredOrders(orders);
        assertEquals(127.5, averageCheck);
    }

    @Test
    public void testGetCustomersWithMoreThanFiveOrders(){
        List<Customer> customers = OrderAnalytics.getCustomersWithMoreThanFiveOrders(orders);
        assertEquals(1, customers.size());
        assertTrue(customers.contains(customer1));
    }
}
