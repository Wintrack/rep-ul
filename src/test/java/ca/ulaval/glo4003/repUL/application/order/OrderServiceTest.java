package ca.ulaval.glo4003.repUL.application.order;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.order.dto.*;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEventNotifier;
import ca.ulaval.glo4003.repUL.application.order.factory.OrderFactory;
import ca.ulaval.glo4003.repUL.application.order.storage.OrderPreparerStorage;
import ca.ulaval.glo4003.repUL.application.order.storage.OrderStorage;
import ca.ulaval.glo4003.repUL.application.order.storage.WaitingOrderQueue;
import ca.ulaval.glo4003.repUL.domain.order.Kitchen;
import ca.ulaval.glo4003.repUL.domain.order.Order;
import ca.ulaval.glo4003.repUL.domain.order.OrderPreparer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static ca.ulaval.glo4003.repUL.application.order.event.OrderEventType.ORDER_ASSEMBLED;
import static java.util.UUID.randomUUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    private OrderStorage orderStorage;

    private OrderPreparerStorage orderPreparerStorage;

    private WaitingOrderQueue waitingOrderQueue;

    private OrderEventNotifier orderEventNotifier;

    private Kitchen kitchen;

    private OrderService orderService;

    private OrderFactory orderFactory;


    @BeforeEach
    void setUp() {
        this.orderStorage = mock();
        this.orderPreparerStorage = mock();
        this.waitingOrderQueue = mock();
        this.orderEventNotifier = mock();
        this.kitchen = mock();
        this.orderFactory = mock();

        ServiceLocator.getInstance().registerService(OrderStorage.class, orderStorage);
        ServiceLocator.getInstance().registerService(OrderPreparerStorage.class, orderPreparerStorage);
        ServiceLocator.getInstance().registerService(WaitingOrderQueue.class, waitingOrderQueue);
        ServiceLocator.getInstance().registerService(OrderEventNotifier.class, orderEventNotifier);
        ServiceLocator.getInstance().registerService(Kitchen.class, kitchen);
        ServiceLocator.getInstance().registerService(OrderFactory.class, orderFactory);

        orderService = new OrderService();
    }

    @Test
    void givenOrderService_whenAddOrderToWaitingList_thenDelegateToServices() {
        // GIVEN
        CreateOrderDto createOrderDto = mock();
        Order order = mock();

        when(orderFactory.toOrder(createOrderDto)).thenReturn(order);

        // WHEN
        orderService.addOrderToWaitingList(createOrderDto);

        // THEN
        verify(orderFactory).toOrder(createOrderDto);
        verify(waitingOrderQueue).addWaitingOrder(any());
    }

    @Test
    void givenOrderService_whenCreateOrdersOfTheDay_thenDelegateToServices() {
        // GIVEN
        Order order = mock();
        List<Order> waitingOrders = List.of(order);
        List<Order> orderToCreate = List.of(order);

        when(waitingOrderQueue.getWaitingOrders()).thenReturn(waitingOrders);
        when(kitchen.selectOrdersOfTheDay(waitingOrders)).thenReturn(orderToCreate);

        // WHEN
        orderService.createOrdersOfTheDay();

        // THEN
        verify(waitingOrderQueue).getWaitingOrders();
        verify(kitchen).selectOrdersOfTheDay(waitingOrders);
        verify(orderStorage).saveOrders(orderToCreate);
        verify(waitingOrderQueue).updateWaitingOrders(waitingOrders);
    }

    @Test
    void givenOrderService_whenSetOrderAssembledOrReady_thenDelegatesToServices() {
        // GIVEN
        SetOrderAssembledOrReadyDto setOrderAssembledOrReadyDto = mock();
        Order order = mock();

        when(orderStorage.getOrderById(any())).thenReturn(order);


        // WHEN
        orderService.setOrderAssembledOrReady(setOrderAssembledOrReadyDto);

        // THEN
        verify(orderStorage).getOrderById(any());
        verify(order).setOrderAssembledOrReady(any());
        verify(orderStorage).saveOrder(order);
    }

    @Test
    void givenOrderService_whenSetOrdersReady_thenDelegateToServices() {
        // GIVEN
        SetOrdersReadyDto setOrdersReadyDto = mock();
        Order order = mock();
        List<Order> orders = List.of(order);

        when(orderStorage.getOrdersByIds(any())).thenReturn(orders);
        when(kitchen.setOrdersReady(orders)).thenReturn(orders);

        // WHEN
        orderService.setOrdersReady(setOrdersReadyDto);

        // THEN
        verify(orderStorage).getOrdersByIds(any());
        verify(orderEventNotifier).notify(eq(ORDER_ASSEMBLED), any());
        verify(kitchen).setOrdersReady(any());
        verify(orderStorage).saveOrders(any());
    }

    @Test
    void givenOrderService_whenSelectOrders_thenDelegateToServices() {
        // GIVEN
        SelectOrderDto selectOrderDto = mock();
        Order order = mock();
        List<Order> orders = List.of(order);
        OrderPreparer orderPreparer = mock();

        when(orderStorage.getOrdersByIds(any())).thenReturn(orders);
        when(orderPreparerStorage.getOrderPreparerByEmail(any())).thenReturn(orderPreparer);
        when(kitchen.selectOrders(orders, orderPreparer)).thenReturn(orders);

        // WHEN
        orderService.selectOrders(selectOrderDto);

        // THEN
        verify(orderStorage).getOrdersByIds(any());
        verify(orderPreparerStorage).getOrderPreparerByEmail(any());
        verify(kitchen).selectOrders(any(), any());
        verify(orderStorage).saveOrders(any());
    }

    @Test
    void givenOrderService_whenConfirmOrders_thenDelegateToServices() {
        // GIVEN
        ConfirmOrdersDto confirmOrdersDto = mock();
        Order order = mock();
        List<Order> orders = List.of(order);
        OrderPreparer orderPreparer = mock();

        when(orderStorage.getOrdersByIds(any())).thenReturn(orders);
        when(orderPreparerStorage.getOrderPreparerByEmail(any())).thenReturn(orderPreparer);
        when(kitchen.abortOrders(orders, orderPreparer)).thenReturn(orders);

        // WHEN
        orderService.confirmOrders(confirmOrdersDto);

        // THEN
        verify(orderStorage).getOrdersByIds(any());
        verify(orderPreparerStorage).getOrderPreparerByEmail(any());
        verify(kitchen).confirmOrders(any(), any());
        verify(orderStorage).saveOrders(any());
    }

    @Test
    void givenOrderService_whenAbortOrders_thenDelegateToServices() {
        // GIVEN
        AbortOrderDto abortOrderDto = mock();
        Order order = mock();
        List<Order> orders = List.of(order);
        OrderPreparer orderPreparer = mock();

        when(orderStorage.getOrdersByIds(any())).thenReturn(orders);
        when(orderPreparerStorage.getOrderPreparerByEmail(any())).thenReturn(orderPreparer);
        when(kitchen.abortOrders(orders, orderPreparer)).thenReturn(orders);

        // WHEN
        orderService.abortOrders(abortOrderDto);

        // THEN
        verify(orderStorage).getOrdersByIds(any());
        verify(orderPreparerStorage).getOrderPreparerByEmail(any());
        verify(kitchen).abortOrders(any(), any());
        verify(orderStorage).saveOrders(any());
    }
}
