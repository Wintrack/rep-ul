package ca.ulaval.glo4003.repUL.domain.order;

import ca.ulaval.glo4003.repUL.domain.order.exception.NotConfirmedOrderException;
import ca.ulaval.glo4003.repUL.domain.order.exception.NotSelectedOrderException;
import ca.ulaval.glo4003.repUL.domain.order.exception.OrderNotFoundException;
import ca.ulaval.glo4003.repUL.domain.order.exception.selectionImpossibleException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.order.status.OrderStatus.*;
import static ca.ulaval.glo4003.repUL.domain.order.type.OrderType.TO_DELIVER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTest {
    @Test
    void givenAnOrderInPreparation_whenSetOrderReady_thenSetOrderToReadyStatus() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );

        // WHEN
        order.setOrderReady();

        // THEN
        assertThat(order.getOrderStatus()).isEqualTo(READY);
    }

    @Test
    void givenOrderAndAPreparer_whenSelectOrder_thenPreparerIsSetToTheOrder() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();

        // WHEN
        order.selectOrder(orderPreparer);

        // THEN
        assertThat(order.getOrderPreparator()).isEqualTo(orderPreparer);
    }

    @Test
    void givenOrderAlreadySelectedAndAPreparer_whenSelectOrder_thenPreparerUnset() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();
        order.selectOrder(orderPreparer);

        // WHEN
        order.selectOrder(orderPreparer);

        // THEN
        assertThat(order.getOrderPreparator()).isEqualTo(null);
    }

    @Test
    void givenOrderNotInPreparationAndAPreparer_whenSelectOrder_thenThrowSelectionImpossibleException() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();

        when(orderPreparer.getName()).thenReturn("Bob the testor");
        order.selectOrder(orderPreparer);
        order.confirmOrder(orderPreparer);

        // WHEN
        assertThatThrownBy(() -> order.selectOrder(orderPreparer))
                .isInstanceOf(selectionImpossibleException.class)
                .hasMessageContaining(
                        "Impossible to select or deselect order," +
                        " order not in preparation"
                );
    }


    @Test
    void givenOrderSelectedAndAPreparer_whenConfirmOrder_thenSetOrderStatusToConfirmed() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();

        when(orderPreparer.getName()).thenReturn("Bob the testor");
        order.selectOrder(orderPreparer);

        // WHEN
        order.confirmOrder(orderPreparer);

        // THEN
        assertThat(order.getOrderStatus()).isEqualTo(CONFIRMED);
    }


    @Test
    void givenOrderNotSelectedAndAPreparer_whenConfirmOrder_thenThrowNotSelectedOrderException() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();

        // WHEN
        assertThatThrownBy(() -> order.confirmOrder(orderPreparer))
                .isInstanceOf(NotSelectedOrderException.class)
                .hasMessageContaining("Order not selected");
    }

    @Test
    void givenOrderSelectedAndAWrongPreparer_whenConfirmOrder_thenThrowOrderNotFoundException() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();
        OrderPreparer wrongOrderPreparer = mock();

        when(orderPreparer.getName()).thenReturn("Bob the testor");
        when(wrongOrderPreparer.getName()).thenReturn("Not Bob the testor");
        order.selectOrder(orderPreparer);

        // WHEN
        assertThatThrownBy(() -> order.confirmOrder(wrongOrderPreparer))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("Order not found");
    }

    @Test
    void givenOrderNotConfirmedAndAPreparer_whenAbortOrder_thenSetOrderStatusToInPreparation() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();

        when(orderPreparer.getName()).thenReturn("Bob the testor");
        order.selectOrder(orderPreparer);
        order.confirmOrder(orderPreparer);

        // WHEN
        order.abortOrder(orderPreparer);


        // THEN
        assertThat(order.getOrderStatus()).isEqualTo(INPREPARATION);
    }

    @Test
    void givenOrderNotConfirmedAndAPreparer_whenAbortOrder_thenThrowNotConfirmedOrderException() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();

        when(orderPreparer.getName()).thenReturn("Bob the testor");
        order.selectOrder(orderPreparer);

        // WHEN
        assertThatThrownBy(() -> order.abortOrder(orderPreparer))
                .isInstanceOf(NotConfirmedOrderException.class)
                .hasMessageContaining("Order not confirmed");
    }

    @Test
    void givenOrderConfirmedAndAWrongPreparer_whenAbortOrder_thenThrowOrderNotFoundException() {
        // GIVEN
        Order order = new Order(
                UUID.randomUUID().toString(),
                "MUSTAFAR",
                mock(),
                LocalDateTime.now(),
                TO_DELIVER
        );
        OrderPreparer orderPreparer = mock();
        OrderPreparer wrongOrderPreparer = mock();

        when(orderPreparer.getName()).thenReturn("Bob the testor");
        when(orderPreparer.getName()).thenReturn("Not Bob the testor");
        order.selectOrder(orderPreparer);
        order.confirmOrder(orderPreparer);

        // WHEN
        assertThatThrownBy(() -> order.abortOrder(wrongOrderPreparer))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("Order not found");
    }
}