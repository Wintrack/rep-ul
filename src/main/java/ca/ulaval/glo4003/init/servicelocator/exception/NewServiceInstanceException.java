package ca.ulaval.glo4003.init.servicelocator.exception;

public class NewServiceInstanceException extends RuntimeException {
    public NewServiceInstanceException(String serviceName) {
        super("Service: " + serviceName + " cannot be created");
    }
}
