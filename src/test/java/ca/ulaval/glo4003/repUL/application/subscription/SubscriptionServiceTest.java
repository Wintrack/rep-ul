package ca.ulaval.glo4003.repUL.application.subscription;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.foodbox.FoodBoxService;
import ca.ulaval.glo4003.repUL.application.payment.PaymentService;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSporadicSubscriptionDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSubscriptionDto;
import ca.ulaval.glo4003.repUL.application.subscription.dto.SubscriptionFoundDto;
import ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventNotifier;
import ca.ulaval.glo4003.repUL.application.subscription.factory.SubscriptionDtoFactory;
import ca.ulaval.glo4003.repUL.application.subscription.factory.SubscriptionEventFactory;
import ca.ulaval.glo4003.repUL.application.subscription.factory.SubscriptionFactory;
import ca.ulaval.glo4003.repUL.application.subscription.storage.SubscriptionStorage;
import ca.ulaval.glo4003.repUL.application.vendorLocation.VendorLocationService;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;
import ca.ulaval.glo4003.repUL.domain.vendorLocation.VendorLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.application.subscription.event.SubscriptionEventType.SUBSCRIPTION_CONFIRMED;
import static org.mockito.Mockito.*;

class SubscriptionServiceTest {
    private PaymentService paymentService;

    private SubscriptionStorage subscriptionStorage;

    private SubscriptionEventNotifier subscriptionEventNotifier;

    private VendorLocationService vendorLocationService;

    private FoodBoxService foodBoxService;

    private SubscriptionFactory subscriptionFactory;

    private SubscriptionEventFactory subscriptionEventFactory;

    private SubscriptionDtoFactory subscriptionDtoFactory;

    private SubscriptionService subscriptionService;

    @BeforeEach
    void beforeEach() {
        this.subscriptionStorage = mock();
        this.vendorLocationService = mock();
        this.foodBoxService = mock();
        this.paymentService = mock();
        this.subscriptionEventNotifier = mock();
        this.subscriptionFactory = mock();
        this.subscriptionEventFactory = mock();
        this.subscriptionDtoFactory = mock();

        ServiceLocator.getInstance().registerService(SubscriptionStorage.class, subscriptionStorage);
        ServiceLocator.getInstance().registerService(VendorLocationService.class, vendorLocationService);
        ServiceLocator.getInstance().registerService(FoodBoxService.class, foodBoxService);
        ServiceLocator.getInstance().registerService(PaymentService.class, paymentService);
        ServiceLocator.getInstance().registerService(SubscriptionEventNotifier.class, subscriptionEventNotifier);
        ServiceLocator.getInstance().registerService(SubscriptionFactory.class, subscriptionFactory);
        ServiceLocator.getInstance().registerService(SubscriptionEventFactory.class, subscriptionEventFactory);
        ServiceLocator.getInstance().registerService(SubscriptionDtoFactory.class, subscriptionDtoFactory);

        subscriptionService = new SubscriptionService();
    }

    @Test
    void givenSubscriptionService_whenSubscribeWeekly_thenDelegateToServices() {
        // GIVEN
        CreateSubscriptionDto createSubscriptionDto = mock();

        when(vendorLocationService.getVendorLocationByLocation(any())).thenReturn(mock());
        when(foodBoxService.getFoodBoxById(any())).thenReturn(mock());
        when(subscriptionFactory.toSubscription(
                any(CreateSubscriptionDto.class),
                any(FoodBox.class),
                any(VendorLocation.class))
        ).thenReturn(mock());

        // WHEN
        subscriptionService.subscribeWeekly(createSubscriptionDto);

        // THEN
        verify(vendorLocationService, times(1)).getVendorLocationByLocation(any());
        verify(foodBoxService, times(1)).getFoodBoxById(any());
        verify(subscriptionFactory, times(1)).toSubscription(
                any(CreateSubscriptionDto.class),
                any(FoodBox.class),
                any(VendorLocation.class)
        );
    }

    @Test
    void givenSubscriptionService_whenSubscribeSporadically_thenDelegateToServices() {
        // GIVEN
        CreateSporadicSubscriptionDto createSubscriptionDto = mock();

        when(vendorLocationService.getVendorLocationByLocation(any())).thenReturn(mock());
        when(foodBoxService.getFoodBoxById(any())).thenReturn(mock());
        when(subscriptionFactory.toSubscription(
                any(CreateSubscriptionDto.class),
                any(FoodBox.class),
                any(VendorLocation.class))
        ).thenReturn(mock());

        // WHEN
        subscriptionService.subscribeSporadically(createSubscriptionDto);

        // THEN
        verify(vendorLocationService, times(1)).getVendorLocationByLocation(any());
        verify(foodBoxService, times(1)).getFoodBoxById(any());
        verify(subscriptionFactory, times(1)).toSubscription(
                any(CreateSporadicSubscriptionDto.class),
                any(FoodBox.class),
                any(VendorLocation.class)
        );
    }

    @Test
    void givenSubscriptionService_whenAcceptFoodBox_thenDelegateToServices() {
        // GIVEN
        String email = "test@test.com";
        String id = UUID.randomUUID().toString();
        Subscription subscription = mock();

        when(subscriptionStorage.getSubscriptionByUserEmailAndId(email, id))
                .thenReturn(subscription);
        when(subscription.getCreditCard()).thenReturn(mock());


        // WHEN
        subscriptionService.acceptFoodBox(email, id);

        // THEN
        verify(subscriptionStorage).getSubscriptionByUserEmailAndId(email, id);
        verify(subscription).setFoodBoxAccepted();
        verify(paymentService).creditCard(any(), anyInt());
        verify(subscriptionEventFactory).toSubscriptionEvent(subscription);
        verify(subscriptionEventNotifier).notify(eq(SUBSCRIPTION_CONFIRMED), any());
        verify(subscriptionStorage).updateSubscription(subscription);
    }

    @Test
    void givenSubscriptionService_whenRefuseSubscription_thenDelegateToServices() {
        // GIVEN
        String email = "test@test.com";
        String id = UUID.randomUUID().toString();
        Subscription subscription = mock();

        when(subscriptionStorage.getSubscriptionByUserEmailAndId(email, id))
                .thenReturn(subscription);

        // WHEN
        subscriptionService.refuseSubscription(email, id);

        // THEN
        verify(subscriptionStorage).getSubscriptionByUserEmailAndId(email, id);
        verify(subscription).setFoodBoxRefused();
        verify(subscriptionStorage).updateSubscription(subscription);
    }

    @Test
    void givenSubscriptionService_whenUpdateSubscriptionNotConfirmed_thenDelegateToServices() {
        // GIVEN
        Subscription subscription = mock();
        List<Subscription> subscriptions = List.of(subscription);

        when(subscriptionStorage.getSubscriptions()).thenReturn(subscriptions);

        // WHEN
        subscriptionService.updateSubscriptionNotConfirmed();

        // THEN
        verify(subscriptionStorage).getSubscriptions();
        verify(subscription).invalidNotConfirmed();
        verify(subscriptionStorage).updateSubscriptions(subscriptions);
    }

    @Test
    void givenSubscriptionService_whenFindSubscriptionByUserEmail_thenDelegateToServices() {
        // GIVEN
        String userEmail = "test@test.com";
        Subscription subscription = mock();
        List<Subscription> subscriptions = List.of(subscription);
        SubscriptionFoundDto subscriptionFoundDto = mock();

        when(subscriptionStorage.getSubscriptionsByUserEmail(userEmail))
                .thenReturn(subscriptions);
        when(subscriptionDtoFactory.toSubscriptionFound(subscription))
                .thenReturn(subscriptionFoundDto);

        // WHEN
        subscriptionService.findSubscriptionByUserEmail(userEmail);

        // THEN
        verify(subscriptionStorage).getSubscriptionsByUserEmail(userEmail);
        verify(subscriptionDtoFactory).toSubscriptionFound(subscription);
    }
}