package ca.ulaval.glo4003.repUL.infrastructure.subscription;

import ca.ulaval.glo4003.repUL.domain.payment.CreditCard;
import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.SubscriptionFrequency;
import ca.ulaval.glo4003.repUL.domain.subscription.frequency.WeeklySubscriptionFrequency;
import ca.ulaval.glo4003.repUL.infrastructure.subscription.exception.SubscriptionNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType.WEEKLY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemorySubscriptionStorageImplTest {

    private InMemorySubscriptionStorageImpl inMemorySubscriptionRepository;

    private static final String ID = UUID.randomUUID().toString();

    private static final String USER_EMAIL = "test@test.com";

    private static final CreditCard CREDIT_CARD = new CreditCard(
            "5415 9052 3492 1230",
            0,
            LocalDate.now().plusDays(42),
            "Bob Thetestor"
    );

    private static final SubscriptionFrequency SUBSCRIPTION_FREQUENCY = new WeeklySubscriptionFrequency(
            LocalDateTime.now()
    );

    private static final String LOCATION = "university street";

    private static final LocalDate START_DATE = LocalDate.now();

    @BeforeEach
    void setup() {
        inMemorySubscriptionRepository = new InMemorySubscriptionStorageImpl();
    }

    @Test
    void givenCreatedSubscription_whenGetSubscriptionByEmail_thenReturnTheSubscriptionCreated() {
        // GIVEN
        Subscription subscription = new Subscription(
                ID,
                USER_EMAIL,
                CREDIT_CARD,
                SUBSCRIPTION_FREQUENCY,
                LOCATION,
                null,
                START_DATE
        );

        inMemorySubscriptionRepository.createSubscription(subscription);

        // WHEN
        List<Subscription> subscriptionsByEmail = inMemorySubscriptionRepository
                .getSubscriptionsByUserEmail(subscription.getUserEmail());

        // THEN
        assertThat(subscriptionsByEmail).isEqualTo(List.of(subscription));
    }

    @Test
    void whenGetSubscriptions_thenReturnAllSubscriptions() {
        // GIVEN
        Subscription subscription = new Subscription(
                ID,
                USER_EMAIL,
                CREDIT_CARD,
                SUBSCRIPTION_FREQUENCY,
                LOCATION,
                null,
                START_DATE
        );

        inMemorySubscriptionRepository.createSubscription(subscription);

        // WHEN
        List<Subscription> subscriptions = inMemorySubscriptionRepository.getSubscriptions();

        // THEN
        List<Subscription> expectedSubscriptions = List.of(subscription);

        assertThat(subscriptions).isEqualTo(expectedSubscriptions);
    }

    @Test
    public void givenIdAndEmail_whenGetSubscriptionById_thenReturnTheSubscriptionFound() {
        // GIVEN
        Subscription subscription = new Subscription(
                ID,
                USER_EMAIL,
                CREDIT_CARD,
                SUBSCRIPTION_FREQUENCY,
                LOCATION,
                null,
                START_DATE
        );

        inMemorySubscriptionRepository.createSubscription(subscription);

        // WHEN
        Subscription subscriptionFound = inMemorySubscriptionRepository
                .getSubscriptionByUserEmailAndId(USER_EMAIL, subscription.getId());

        // THEN
        assertThat(subscriptionFound).isEqualTo(subscription);
    }

    @Test
    public void givenWrongId_whenGetSubscriptionById_thenThrowSubscriptionNotFound() {
        // GIVEN
        Subscription subscription = new Subscription(
                ID,
                USER_EMAIL,
                CREDIT_CARD,
                SUBSCRIPTION_FREQUENCY,
                LOCATION,
                null,
                START_DATE
        );

        inMemorySubscriptionRepository.createSubscription(subscription);

        // WHEN
        assertThatThrownBy(
                () -> inMemorySubscriptionRepository.getSubscriptionByUserEmailAndId(USER_EMAIL, "Wrong id"))
                .isInstanceOf(SubscriptionNotFound.class)
                .hasMessageContaining("Subscription not found");
    }

    @Test
    public void givenWrongEmail_whenGetSubscriptionById_thenThrowSubscriptionNotFound() {
        // GIVEN
        Subscription subscription = new Subscription(
                ID,
                USER_EMAIL,
                CREDIT_CARD,
                SUBSCRIPTION_FREQUENCY,
                LOCATION,
                null,
                START_DATE
        );

        inMemorySubscriptionRepository.createSubscription(subscription);

        // WHEN
        assertThatThrownBy(
                () -> inMemorySubscriptionRepository
                        .getSubscriptionByUserEmailAndId("wrong email", subscription.getId()))
                .isInstanceOf(SubscriptionNotFound.class)
                .hasMessageContaining("Subscription not found");
    }

    @Test
    public void givenSubscription_whenUpdateSubscription_thenUpdateSubscription() {
        // GIVEN
        Subscription subscriptionCreated = new Subscription(
                ID,
                USER_EMAIL,
                CREDIT_CARD,
                SUBSCRIPTION_FREQUENCY,
                LOCATION,
                null,
                START_DATE
        );
        Subscription subscriptionUpdated = new Subscription(
                null,
                "test@test.com",
                CREDIT_CARD,
                subscriptionCreated.getFrequency(),
                subscriptionCreated.getLocation(),
                subscriptionCreated.getFoodBox(),
                subscriptionCreated.getStartDate()
        );

        inMemorySubscriptionRepository.createSubscription(subscriptionCreated);
        subscriptionUpdated.setId(subscriptionCreated.getId());
        // WHEN
        inMemorySubscriptionRepository.updateSubscription(subscriptionUpdated);
        List<Subscription> subscriptionsByEmail = inMemorySubscriptionRepository
                .getSubscriptionsByUserEmail(subscriptionUpdated.getUserEmail());


        // THEN
        assertThat(subscriptionsByEmail).isEqualTo(List.of(subscriptionUpdated));
    }

    @Test
    public void givenUnknownSubscription_whenUpdateSubscription_thenThrowSubscriptionNotFound() {
        // GIVEN
        Subscription subscription = new Subscription(
                ID,
                USER_EMAIL,
                CREDIT_CARD,
                SUBSCRIPTION_FREQUENCY,
                LOCATION,
                null,
                START_DATE
        );

        // THEN
        assertThatThrownBy(() -> inMemorySubscriptionRepository.updateSubscription(subscription))
                .isInstanceOf(SubscriptionNotFound.class)
                .hasMessageContaining("Subscription not found");
    }

    @Test
    public void givenSubscriptions_whenUpdateSubscriptions_thenUpdateSubscription() {
        // GIVEN
        List<Subscription> subscriptionsCreated = List.of(
                new Subscription(
                        ID,
                        USER_EMAIL,
                        CREDIT_CARD,
                        SUBSCRIPTION_FREQUENCY,
                        LOCATION,
                        null,
                        START_DATE
                )
        );
        List<Subscription> subscriptionsUpdated = List.of(
                new Subscription(
                        null,
                        "test@test.com",
                        CREDIT_CARD,
                        subscriptionsCreated.get(0).getFrequency(),
                        subscriptionsCreated.get(0).getLocation(),
                        subscriptionsCreated.get(0).getFoodBox(),
                        subscriptionsCreated.get(0).getStartDate()
                ));

        subscriptionsCreated.forEach(inMemorySubscriptionRepository::createSubscription);
        subscriptionsUpdated.get(0).setId(subscriptionsCreated.get(0).getId());

        // WHEN
        inMemorySubscriptionRepository.updateSubscriptions(subscriptionsUpdated);
        List<Subscription> subscriptionsByEmail = inMemorySubscriptionRepository.getSubscriptions();


        // THEN
        assertThat(subscriptionsByEmail).isEqualTo(subscriptionsUpdated);
    }

}