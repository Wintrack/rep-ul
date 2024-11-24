package ca.ulaval.glo4003.repUL.infrastructure.order;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.order.Order;
import ca.ulaval.glo4003.repUL.domain.order.exception.OrderNotFoundException;
import ca.ulaval.glo4003.repUL.domain.order.type.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType.STANDARD;
import static ca.ulaval.glo4003.repUL.domain.order.type.OrderType.TO_DELIVER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderStorageImplTest {
    private static final String EMAIL = "test@test.com";

    private static final String DESTINATION = "42 bob street";

    private static final FoodBox FOOD_BOX = new FoodBox(
            UUID.randomUUID().toString(),
            STANDARD,
            List.of()
    );

    private static final LocalDateTime DELIVERY_DATE_TIME = LocalDateTime.now();

    private OrderStorageImpl orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderStorageImpl();
    }

    @Test
    void givenOrderSaved_whenGetOrderById_thenReturnOrder() throws OrderNotFoundException {
        // GIVEN
        Order order = new Order(EMAIL, DESTINATION, FOOD_BOX, DELIVERY_DATE_TIME, TO_DELIVER);

        orderRepository.saveOrder(order);
        // WHEN
        Order orderFound = orderRepository.getOrderById(order.getId());

        // THEN
        assertThat(orderFound).isEqualTo(order);
    }

    @Test
    void givenWrongId_whenGetOrderById_thenThrowOrderNotFoundException() throws OrderNotFoundException {
        // GIVEN
        Order order = new Order(EMAIL, DESTINATION, FOOD_BOX, DELIVERY_DATE_TIME, TO_DELIVER);

        orderRepository.saveOrder(order);
        // WHEN
        assertThatThrownBy(
                () -> orderRepository.getOrderById("wrong id"))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("Order not found");
    }

    @Test
    void givenOrdersSaved_whenGetAllOrders_thenReturnAllOrders() throws OrderNotFoundException {
        // GIVEN
        List<Order> orders = List.of(
                new Order(EMAIL, DESTINATION, FOOD_BOX, DELIVERY_DATE_TIME, TO_DELIVER)
        );

        orderRepository.saveOrders(orders);
        // WHEN
        List<Order> ordersFound = orderRepository.getAllOrder();

        // THEN
        assertThat(ordersFound).isEqualTo(orders);
    }

    @Test
    void givenIds_whenGetOrdersById_thenReturnAllOrdersFound() throws OrderNotFoundException {
        // GIVEN
        List<Order> orders = List.of(
                new Order(EMAIL, DESTINATION, FOOD_BOX, DELIVERY_DATE_TIME, TO_DELIVER)
        );

        orderRepository.saveOrders(orders);

        // WHEN
        List<Order> ordersFound = orderRepository.getOrdersByIds(
                List.of(orders.get(0).getId())
        );

        // THEN
        assertThat(ordersFound).isEqualTo(orders);
    }

    @Test
    void givenWrongIds_whenGetOrdersById_thenThrowOrderNotFoundException() throws OrderNotFoundException {
        // GIVEN
        List<Order> orders = List.of(
                new Order(EMAIL, DESTINATION, FOOD_BOX, DELIVERY_DATE_TIME, TO_DELIVER)
        );

        orderRepository.saveOrders(orders);

        // WHEN
        assertThatThrownBy(
                () -> orderRepository.getOrdersByIds(List.of("Wrong id")))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("Order not found");
    }
}