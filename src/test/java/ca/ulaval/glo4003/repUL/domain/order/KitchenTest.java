package ca.ulaval.glo4003.repUL.domain.order;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class KitchenTest {
    @Test
    void givenKitchenAndOrders_whenSelectOrdersOfTheDay_thenReturnOrderToCreate() {
        // GIVEN
        Order orderOfTheNextDay = mock();
        Order orderOfAfterTheNextDay = mock();
        List<Order> orders = new ArrayList<>(List.of(orderOfTheNextDay, orderOfAfterTheNextDay));
        Kitchen kitchen = new Kitchen();

        when(orderOfTheNextDay.isOrderOfTheNextDay()).thenReturn(true);
        when(orderOfAfterTheNextDay.isOrderOfTheNextDay()).thenReturn(false);

        // WHEN
        List<Order> ordersOfTheDay = kitchen.selectOrdersOfTheDay(orders);

        // THEN
        List<Order> expectedResult = List.of(orderOfTheNextDay);

        assertThat(ordersOfTheDay).isEqualTo(expectedResult);
    }

    @Test
    void givenKitchenAndOrders_whenSetOrdersReady_thenCallSetOrderReadyOnOrders() {
        // GIVEN
        Order order = mock();
        List<Order> orders = List.of(order);
        Kitchen kitchen = new Kitchen();

        // WHEN
        kitchen.setOrdersReady(orders);

        // THEN
        verify(order, times(1)).setOrderReady();
    }

    @Test
    void givenKitchenAndOrders_whenConfirmOrders_thenCallConfirmOrderOnOrders() {
        // GIVEN
        Order order = mock();
        List<Order> orders = List.of(order);
        OrderPreparer orderPreparer = mock();
        Kitchen kitchen = new Kitchen();

        // WHEN
        kitchen.confirmOrders(orders, orderPreparer);

        // THEN
        verify(order, times(1)).confirmOrder(orderPreparer);
    }

    @Test
    void givenKitchenAndOrders_whenAbortOrder_thenCallAbortOrderOnOrders() {
        // GIVEN
        Order order = mock();
        List<Order> orders = List.of(order);
        OrderPreparer orderPreparer = mock();
        Kitchen kitchen = new Kitchen();

        // WHEN
        kitchen.abortOrders(orders, orderPreparer);

        // THEN
        verify(order, times(1)).abortOrder(orderPreparer);
    }
}