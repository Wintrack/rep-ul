package ca.ulaval.glo4003.repUL.api.subscription;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.AuthenticatedUserPrincipal;
import ca.ulaval.glo4003.repUL.api.config.authentication.JwtSecurityContext;
import ca.ulaval.glo4003.repUL.api.subscription.dto.SubscriptionAddCreditCardRequest;
import ca.ulaval.glo4003.repUL.api.subscription.dto.SubscriptionFrequencyRequest;
import ca.ulaval.glo4003.repUL.api.subscription.dto.SubscriptionWeeklyRequest;
import ca.ulaval.glo4003.repUL.application.subscription.SubscriptionService;
import ca.ulaval.glo4003.repUL.application.subscription.dto.CreateSubscriptionDto;
import ca.ulaval.glo4003.repUL.domain.subscription.Subscription;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.subscription.type.SubscriptionFrequencyType.WEEKLY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SubscriptionResourceImplTest {
    private static final SubscriptionAddCreditCardRequest CREDIT_CARD = new SubscriptionAddCreditCardRequest(
            "5415 9052 3492 1230",
            0, LocalDate.now().plusDays(42),
            "Bob Thetestor"
    );

    private static final SubscriptionFrequencyRequest SUBSCRIPTION_FREQUENCY = new SubscriptionFrequencyRequest(
            WEEKLY,
            LocalDateTime.now()
    );

    private static final String FOODBOX_ID = UUID.randomUUID().toString();

    private static final String LOCATION = "university street";

    private SubscriptionResourceImpl subscriptionResource;

    private SubscriptionService subscriptionService;

    private SecurityContext securityContext;

    @BeforeEach
    void setup() {
        subscriptionService = mock();
        ServiceLocator.getInstance().registerService(SubscriptionService.class, subscriptionService);
        subscriptionResource = new SubscriptionResourceImpl();
        securityContext = new JwtSecurityContext(
                new AuthenticatedUserPrincipal("test@test.com")
        );
    }

    @Test
    void givenUserSubscriptionRequest_whenSubscribe_thenDelegateToSubscriptionService() {
        // GIVEN
        SubscriptionWeeklyRequest subscriptionWeeklyRequest = new SubscriptionWeeklyRequest(
                CREDIT_CARD,
                FOODBOX_ID,
                LocalDateTime.now(),
                LOCATION
        );

        doNothing().when(subscriptionService).subscribeWeekly(any(CreateSubscriptionDto.class));

        // WHEN
        subscriptionResource.subscribeWeekly(securityContext, subscriptionWeeklyRequest);

        // THEN
        verify(subscriptionService, times(1)).subscribeWeekly(any(CreateSubscriptionDto.class));
    }

    @Test
    void givenUserSubscriptionRequest_whenSubscribe_thenReturnCreatedStatus() {
        // GIVEN
        SubscriptionWeeklyRequest subscriptionWeeklyRequest = new SubscriptionWeeklyRequest(
                CREDIT_CARD,
                FOODBOX_ID,
                LocalDateTime.now(),
                LOCATION
        );

        doNothing().when(subscriptionService).subscribeWeekly(any(CreateSubscriptionDto.class));
        // WHEN
        Response response = subscriptionResource.subscribeWeekly(securityContext, subscriptionWeeklyRequest);

        // THEN
        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
    }

    @Test
    void givenUser_whenAcceptFoodBox_thenDelegateToTheSubscriptionService() {
        // GIVEN
        String id = UUID.randomUUID().toString();

        doNothing().when(subscriptionService).acceptFoodBox("test@test.com", id);

        // WHEN
        subscriptionResource.acceptFoodBox(securityContext, id);

        // THEN
        verify(subscriptionService, times(1)).acceptFoodBox("test@test.com", id);
    }

    @Test
    void givenUser_whenAcceptFoodBox_thenReturnNoContent() {
        // GIVEN
        String id = UUID.randomUUID().toString();

        doNothing().when(subscriptionService).acceptFoodBox("test@test.com", id);

        // WHEN
        Response response = subscriptionResource.acceptFoodBox(securityContext, id);

        // THEN
        assertThat(response.getStatus()).isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    void givenUser_whenRefuseFoodBox_thenDelegateToTheSubscriptionService() {
        // GIVEN
        String id = UUID.randomUUID().toString();

        doNothing().when(subscriptionService).refuseSubscription("test@test.com", id);

        // WHEN
        subscriptionResource.refuseSubscription(securityContext, id);

        // THEN
        verify(subscriptionService, times(1)).refuseSubscription("test@test.com", id);
    }

    @Test
    void givenUser_whenRefuseFoodBox_thenReturnNoContent() {
        // GIVEN
        String id = UUID.randomUUID().toString();

        doNothing().when(subscriptionService).refuseSubscription("test@test.com", id);

        // WHEN
        Response response = subscriptionResource.refuseSubscription(securityContext, id);

        // THEN
        assertThat(response.getStatus()).isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    }

}