package pl.jaworskimateusz.machineapi.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String serviceName, long id) {
        super("Could not find element from " + serviceName + " with id: " + id);
    }
}
