package ca.ulaval.glo4003.init.registry.foodbox;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.foodbox.infra.FoodBoxRepository;
import ca.ulaval.glo4003.repUL.infrastructure.foodbox.FoodBoxRepositoryImpl;
import ca.ulaval.glo4003.repUL.infrastructure.foodbox.data.FoodBoxDataGenerator;

public class FoodboxRegistry {
    public void register() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        serviceLocator.registerService(FoodBoxRepository.class, new FoodBoxRepositoryImpl());
        FoodBoxDataGenerator.createMockData();
    }
}
