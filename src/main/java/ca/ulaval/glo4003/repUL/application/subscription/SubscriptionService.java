package ca.ulaval.glo4003.repUL.application.subscription;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.foodbox.FoodBoxService;
import ca.ulaval.glo4003.repUL.application.payment.PaymentService;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSporadicSubscriptionDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSubscriptionDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.SubscriptionFoundDto;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventNotifier;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventType;
import ca.ulaval.glo4003.repUL.application.subscription.factory.SubscriptionDtoFactory;
import ca.ulaval.glo4003.repUL.application.subscription.factory.SubscriptionEventFactory;
import ca.ulaval.glo4003.repUL.application.subscription.factory.SubscriptionFactory;
import ca.ulaval.glo4003.repUL.application.subscription.storage.SubscriptionStorage;
import ca.ulaval.glo4003.repUL.application.vendorLocation.VendorLocationService;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;

import java.util.List;

public class SubscriptionService {
    private final PaymentService paymentService;

    private final SubscriptionStorage subscriptionStorage;

    private final SubscriptionEventNotifier subscriptionEventNotifier;

    private final VendorLocationService vendorLocationService;

    private final FoodBoxService foodBoxService;

    private final SubscriptionFactory subscriptionFactory;

    private final SubscriptionEventFactory subscriptionEventFactory;

    private final SubscriptionDtoFactory subscriptionDtoFactory;

    public SubscriptionService() {
        this.subscriptionStorage = ServiceLocator.getInstance().getService(SubscriptionStorage.class);
        this.vendorLocationService = ServiceLocator.getInstance().getService(VendorLocationService.class);
        this.foodBoxService = ServiceLocator.getInstance().getService(FoodBoxService.class);
        this.paymentService = ServiceLocator.getInstance().getService(PaymentService.class);
        this.subscriptionEventNotifier = ServiceLocator.getInstance().getService(SubscriptionEventNotifier.class);
        this.subscriptionFactory = ServiceLocator.getInstance().getService(SubscriptionFactory.class);
        this.subscriptionEventFactory = ServiceLocator.getInstance().getService(SubscriptionEventFactory.class);
        this.subscriptionDtoFactory = ServiceLocator.getInstance().getService(SubscriptionDtoFactory.class);
    }

    public void subscribeWeekly(CreateSubscriptionDto createSubscriptionDto) {
        VendorLocation vendorLocation = vendorLocationService
                .getVendorLocationByLocation(createSubscriptionDto.location());
        FoodBox foodBox = foodBoxService.getFoodBoxById(createSubscriptionDto.foodBoxId());
        Subscription subscription = subscriptionFactory.toSubscription(
                createSubscriptionDto,
                foodBox,
                vendorLocation
        );

        subscriptionStorage.createSubscription(subscription);
    }

    public void subscribeSporadically(CreateSporadicSubscriptionDto createSporadicSubscriptionDto) {
        VendorLocation vendorLocation = vendorLocationService
                .getVendorLocationByLocation(createSporadicSubscriptionDto.location());
        FoodBox foodBox = foodBoxService.getFoodBoxById(createSporadicSubscriptionDto.foodBoxId());
        Subscription subscription = subscriptionFactory.toSubscription(
                createSporadicSubscriptionDto,
                foodBox,
                vendorLocation
        );

        subscriptionStorage.createSubscription(subscription);
    }

    public void acceptFoodBox(String email, String id) {
        Subscription subscription = subscriptionStorage.getSubscriptionByUserEmailAndId(email, id);

        subscription.setFoodBoxAccepted();
        paymentService.creditCard(subscription.getCreditCard(), 75);
        subscriptionEventNotifier.notify(
                SubscriptionEventType.SUBSCRIPTION_CONFIRMED,
                subscriptionEventFactory.toSubscriptionEvent(subscription)
        );
        subscriptionStorage.updateSubscription(subscription);
    }

    public void refuseSubscription(String email, String id) {
        Subscription subscription = subscriptionStorage.getSubscriptionByUserEmailAndId(email, id);

        subscription.setFoodBoxRefused();
        subscriptionStorage.updateSubscription(subscription);
    }

    public void updateSubscriptionNotConfirmed() {
        List<Subscription> subscriptions = subscriptionStorage.getSubscriptions();

        subscriptions.forEach(Subscription::invalidNotConfirmed);
        subscriptionStorage.updateSubscriptions(subscriptions);
    }

    public List<SubscriptionFoundDto> findSubscriptionByUserEmail(String email) {
        List<Subscription> subscriptionsByUserEmail = subscriptionStorage.getSubscriptionsByUserEmail(email);

        return subscriptionsByUserEmail
                .stream().map(subscriptionDtoFactory::toSubscriptionFound)
                .toList();
    }
}
