package ca.ulaval.glo4003.repUL.api.foodbox;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.annotation.Protected;
import ca.ulaval.glo4003.repUL.api.foodbox.assembler.FoodBoxDtoAssembler;
import ca.ulaval.glo4003.repUL.api.foodbox.assembler.ingredient.IngredientDtoAssembler;
import ca.ulaval.glo4003.repUL.api.foodbox.dto.FoodBoxFoundResponse;
import ca.ulaval.glo4003.repUL.api.foodbox.dto.ingredient.IngredientFoundResponse;
import ca.ulaval.glo4003.repUL.application.foodbox.FoodBoxService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/foodbox")
@Protected
public class FoodBoxResourceImpl {
    private final FoodBoxService foodBoxService;

    private final FoodBoxDtoAssembler foodBoxDtoAssembler;

    private final IngredientDtoAssembler ingredientDtoAssembler;

    public FoodBoxResourceImpl() {
        this.foodBoxService = ServiceLocator.getInstance().getService(FoodBoxService.class);
        this.foodBoxDtoAssembler = ServiceLocator.getInstance().getService(FoodBoxDtoAssembler.class);
        this.ingredientDtoAssembler = ServiceLocator.getInstance().getService(IngredientDtoAssembler.class);
    }

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFoodBoxes() {
        List<FoodBoxFoundResponse> foodBoxFoundResponses = foodBoxService
                .getFoodBoxes()
                .stream().map(foodBoxDtoAssembler::toFoodBoxFound)
                .toList();

        return Response.status(Response.Status.OK)
                .entity(foodBoxFoundResponses)
                .build();
    }

    @GET()
    @Path("{id}/recipe/{name}/ingredient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredients(
            @PathParam("id") String id,
            @PathParam("name") String ingredientName
    ) {
        List<IngredientFoundResponse> ingredientFoundResponses = foodBoxService
                .getIngredients(id, ingredientName)
                .stream().map(ingredientDtoAssembler::toIngredientFoundResponse)
                .toList();

        return Response.status(Response.Status.OK)
                .entity(ingredientFoundResponses)
                .build();
    }
}
