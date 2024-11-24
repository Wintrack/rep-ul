package ca.ulaval.glo4003.repUL.api.foodbox;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.foodbox.dto.FoodBoxFoundResponse;
import ca.ulaval.glo4003.repUL.application.foodbox.FoodBoxService;
import ca.ulaval.glo4003.repUL.domain.foodbox.FoodBox;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoodBoxResourceImplTest {
    private FoodBoxService foodBoxService;

    private final FoodBoxResourceImpl foodBoxResource;


    public FoodBoxResourceImplTest() {
        foodBoxService = mock();
        ServiceLocator.getInstance().registerService(FoodBoxService.class, foodBoxService);
        foodBoxResource = new FoodBoxResourceImpl();
    }

    @Test
    void whenGivenBoxes_thenDelegateToFoodBoxService() {
        // GIVEN
        String id = UUID.randomUUID().toString();
        List<FoodBox> foodBox = List.of(
                new FoodBox(id, STANDARD, List.of())
        );

        when(foodBoxService.getFoodBoxes()).thenReturn(foodBox);
        // WHEN
        Response response = foodBoxResource.getFoodBoxes();

        // THEN
        List<FoodBoxFoundResponse> expectedEntity = List.of(
                new FoodBoxFoundResponse(
                        id,
                        STANDARD,
                        List.of()
                )
        );

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.getEntity()).isEqualTo(expectedEntity);
    }
}