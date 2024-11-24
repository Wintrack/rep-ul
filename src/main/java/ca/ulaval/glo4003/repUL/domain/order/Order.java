package ca.ulaval.glo4003.repUL.domain.order;

import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.order.exception.*;
import ca.ulaval.glo4003.repUL.domain.order.status.OrderStatus;
import ca.ulaval.glo4003.repUL.domain.order.type.OrderType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.order.status.OrderStatus.*;

public class Order {
    private String id;

    private String userEmail;

    private String destination;

    private FoodBox foodBox;

    private OrderStatus orderStatus;

    private OrderPreparer orderPreparer;

    private LocalDateTime deliveryDateTime;

    private OrderType orderType;

    public Order(
            String userEmail,
            String destination,
            FoodBox foodBox,
            LocalDateTime deliveryDateTime,
            OrderType orderType
    ) {
        this.userEmail = userEmail;
        this.destination = destination;
        this.id = UUID.randomUUID().toString();
        this.orderStatus = INPREPARATION;
        this.foodBox = foodBox;
        this.orderPreparer = null;
        this.deliveryDateTime = deliveryDateTime;
        this.orderType = orderType;
    }

    public String getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getDestination() {
        return destination;
    }

    public FoodBox getFoodBox() {
        return foodBox;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderPreparer getOrderPreparator() {
        return orderPreparer;
    }

    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderAssembledOrReady(OrderStatus orderStatus) {
        if (orderStatus == ASSEMBLED || orderStatus == READY) {
            this.orderStatus = orderStatus;
        }
        throw new NotAssembledOrReadyOrderException();
    }

    public void setOrderReady() {
        orderStatus = READY;
    }

    public void selectOrder(OrderPreparer orderPreparer) {
        if (orderStatus != INPREPARATION) {
            throw new selectionImpossibleException();
        }
        if (this.orderPreparer != null) {
            this.orderPreparer = null;
            return;
        }
        this.orderPreparer = orderPreparer;
    }


    public void confirmOrder(OrderPreparer orderPreparer) {
        if (this.orderPreparer == null) {
            throw new NotSelectedOrderException();
        }
        if (!this.orderPreparer.getName().equals(orderPreparer.getName())) {
            throw new OrderNotFoundException();
        }

        orderStatus = OrderStatus.CONFIRMED;
    }

    public void abortOrder(OrderPreparer orderPreparer) {
        if (!this.orderStatus.equals(CONFIRMED)) {
            throw new NotConfirmedOrderException();
        }
        if (!this.orderPreparer.getName().equals(orderPreparer.getName())) {
            throw new OrderNotFoundException();
        }
        orderStatus = INPREPARATION;
    }

    public boolean isOrderOfTheNextDay() {
        LocalDate dateToCompare = LocalDate.now();
        LocalDate deliveryDate = LocalDate.from(deliveryDateTime);

        if (dateToCompare.getDayOfWeek() == DayOfWeek.FRIDAY) {
            dateToCompare = dateToCompare.plusDays(3);
        } else {
            dateToCompare = dateToCompare.plusDays(1);
        }
        return dateToCompare.equals(deliveryDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(foodBox, order.foodBox) &&
                orderStatus == order.orderStatus &&
                Objects.equals(orderPreparer, order.orderPreparer)
                && Objects.equals(deliveryDateTime, order.deliveryDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                foodBox,
                orderStatus,
                orderPreparer,
                deliveryDateTime
        );
    }
}
