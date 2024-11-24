package ca.ulaval.glo4003.repUL.application.order;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.order.dto.*;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEvent;
import ca.ulaval.glo4003.repUL.application.order.event.OrderEventNotifier;
import ca.ulaval.glo4003.repUL.application.order.exception.OrderPreparerNotFoundException;
import ca.ulaval.glo4003.repUL.application.order.factory.OrderFactory;
import ca.ulaval.glo4003.repUL.application.order.storage.OrderPreparerStorage;
import ca.ulaval.glo4003.repUL.application.order.storage.OrderStorage;
import ca.ulaval.glo4003.repUL.application.order.storage.WaitingOrderQueue;
import ca.ulaval.glo4003.repUL.domain.order.Order;
import ca.ulaval.glo4003.repUL.domain.order.Kitchen;
import ca.ulaval.glo4003.repUL.domain.order.OrderPreparer;

import java.util.List;

import static ca.ulaval.glo4003.repUL.application.order.event.OrderEventType.ORDER_ASSEMBLED;

public class OrderService {
    private final OrderStorage orderStorage;

    private final OrderPreparerStorage orderPreparerStorage;

    private final WaitingOrderQueue waitingOrderQueue;

    private final OrderEventNotifier orderEventNotifier;

    private final Kitchen kitchen;

    private final OrderFactory orderFactory;

    public OrderService() {
        this.orderStorage = ServiceLocator.getInstance().getService(OrderStorage.class);
        this.orderPreparerStorage = ServiceLocator.getInstance().getService(OrderPreparerStorage.class);
        this.waitingOrderQueue = ServiceLocator.getInstance().getService(WaitingOrderQueue.class);
        this.orderEventNotifier = ServiceLocator.getInstance().getService(OrderEventNotifier.class);
        this.kitchen = ServiceLocator.getInstance().getService(Kitchen.class);
        this.orderFactory = ServiceLocator.getInstance().getService(OrderFactory.class);
    }

    public void addOrderToWaitingList(CreateOrderDto createOrderDto) {
        Order order = orderFactory.toOrder(createOrderDto);

        waitingOrderQueue.addWaitingOrder(order);
    }

    public void createOrdersOfTheDay() {
        List<Order> waitingOrders = waitingOrderQueue.getWaitingOrders();
        List<Order> orderToCreate = kitchen.selectOrdersOfTheDay(waitingOrders);

        orderStorage.saveOrders(orderToCreate);
        waitingOrderQueue.updateWaitingOrders(waitingOrders);
    }

    public void setOrderAssembledOrReady(SetOrderAssembledOrReadyDto setOrderAssembledOrReadyDto) {
        Order order = orderStorage.getOrderById(setOrderAssembledOrReadyDto.id());

        order.setOrderAssembledOrReady(setOrderAssembledOrReadyDto.orderStatus());
        orderStorage.saveOrder(order);
    }

    public void setOrdersReady(SetOrdersReadyDto setOrdersReadyDto) {
        List<Order> orders = orderStorage.getOrdersByIds(setOrdersReadyDto.ordersIds());
        List<Order> ordersReady = kitchen.setOrdersReady(orders);

        orderEventNotifier.notify(ORDER_ASSEMBLED, new OrderEvent(ordersReady));
        orderStorage.saveOrders(ordersReady);
    }

    public void selectOrders(SelectOrderDto selectOrderDto) {
        List<Order> orders = orderStorage.getOrdersByIds(selectOrderDto.ordersIds());
        OrderPreparer orderPreparer = orderPreparerStorage
                .getOrderPreparerByEmail(selectOrderDto.orderPreparerEmail());

        if (orderPreparer == null) {
            throw new OrderPreparerNotFoundException();
        }

        List<Order> ordersSelected = kitchen.selectOrders(orders, orderPreparer);

        orderStorage.saveOrders(ordersSelected);
    }

    public void confirmOrders(ConfirmOrdersDto confirmOrdersDto) {
        List<Order> orders = orderStorage.getOrdersByIds(confirmOrdersDto.ordersIds());
        OrderPreparer orderPreparer = orderPreparerStorage
                .getOrderPreparerByEmail(confirmOrdersDto.orderPreparerEmail());

        if (orderPreparer == null) {
            throw new OrderPreparerNotFoundException();
        }

        List<Order> ordersConfirmed = kitchen.confirmOrders(orders, orderPreparer);

        orderStorage.saveOrders(ordersConfirmed);
    }

    public void abortOrders(AbortOrderDto abortOrderDto) {
        List<Order> orders = orderStorage.getOrdersByIds(abortOrderDto.ordersIds());
        OrderPreparer orderPreparer = orderPreparerStorage
                .getOrderPreparerByEmail(abortOrderDto.orderPreparerEmail());

        if (orderPreparer == null) {
            throw new OrderPreparerNotFoundException();
        }

        List<Order> ordersConfirmed = kitchen.abortOrders(orders, orderPreparer);

        orderStorage.saveOrders(ordersConfirmed);
    }

    public List<Order> getAllOrder() {
        return orderStorage.getAllOrder();
    }

    public List<Order> getUserOrders(String email) {
        return orderStorage.getAllOrderByEmail(email);
    }
}