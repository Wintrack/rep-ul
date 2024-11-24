package ca.ulaval.glo4003.init.servicelocator;

import ca.ulaval.glo4003.init.servicelocator.exception.NewServiceInstanceException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ServiceLocator {

    private static ServiceLocator INSTANCE;

    private final HashMap<Class<?>, Object> services;

    public ServiceLocator() {
        services = new HashMap<>();
    }

    public static synchronized ServiceLocator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceLocator();
        }
        return INSTANCE;
    }

    public <T, U extends T> void registerService(Class<T> serviceType, U service) {
        services.put(serviceType, service);
    }


    public <T> T getService(Class<T> service) {
        Object serviceFound = services.get(service);

        if (serviceFound == null) {
            return generateNewInstance(service);
        }
        return (T) serviceFound;
    }

    private <T> T generateNewInstance(Class<T> service) {
        try {
            T newInstance = service.getDeclaredConstructor().newInstance();

            services.put(service, newInstance);
            return newInstance;
        } catch (InstantiationException | NoSuchMethodException |
                 InvocationTargetException | IllegalAccessException e) {
            throw new NewServiceInstanceException(service.getName());
        }
    }

}