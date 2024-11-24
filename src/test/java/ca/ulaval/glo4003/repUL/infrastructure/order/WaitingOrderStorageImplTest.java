package ca.ulaval.glo4003.repUL.infrastructure.order;

import ca.ulaval.glo4003.repUL.application.order.storage.WaitingOrderQueue;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.order.Order;
import ca.ulaval.glo4003.repUL.domain.order.type.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType.STANDARD;
import static ca.ulaval.glo4003.repUL.domain.order.type.OrderType.TO_DELIVER;
import static org.assertj.core.api.Assertions.assertThat;

class WaitingOrderStorageImplTest {
    private static final String EMAIL = "test@test.com";

    private static final String DESTINATION = "42 bob street";

    private static final FoodBox FOOD_BOX = new FoodBox(
            UUID.randomUUID().toString(),
            STANDARD,
            List.of()
    );

    private static final LocalDateTime DELIVERY_DATE_TIME = LocalDateTime.now();

    private WaitingOrderQueue waitingOrderQueue;

    @BeforeEach
    void setup() {
        waitingOrderQueue = new WaitingOrderQueueImpl();
    }

    @Test
    void givenWaitingOrderAdded_whenGetWaitingOrders_thenGetAllWaitingOrders() {
        // GIVEN
        Order order = new Order(EMAIL, DESTINATION, FOOD_BOX, DELIVERY_DATE_TIME, TO_DELIVER);

        waitingOrderQueue.addWaitingOrder(order);

        // WHEN
        List<Order> waitingOrders = waitingOrderQueue.getWaitingOrders();

        // THEN
        assertThat(waitingOrders).isEqualTo(List.of(order));
    }

    @Test
    void givenWaitingOrderAdded_whenUpdateWaitingOrders_thenUpdateAllWaitingOrders() {
        // GIVEN
        Order order = new Order(EMAIL, DESTINATION, FOOD_BOX, DELIVERY_DATE_TIME, TO_DELIVER);

        waitingOrderQueue.addWaitingOrder(order);

        // WHEN
        waitingOrderQueue.updateWaitingOrders(List.of());
        List<Order> waitingOrders = waitingOrderQueue.getWaitingOrders();

        // THEN
        assertThat(waitingOrders).isEqualTo(List.of());
    }

}