package pl.jaworskimateusz.machineapi.mapper;

import pl.jaworskimateusz.machineapi.dto.ServiceDto;
import pl.jaworskimateusz.machineapi.model.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceMapper {

    public static ServiceDto mapToServiceDto(Service service) {
        return new ServiceDto(
                service.getServiceId(),
                service.getDate(),
                service.getComponentName(),
                service.getDescription(),
                service.getResult(),
                service.getMachine() != null ? service.getMachine().getMachineId() : null
        );
    }
}
