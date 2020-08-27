package pl.jaworskimateusz.machineapi.mapper;

import pl.jaworskimateusz.machineapi.dto.ServiceDto;
import pl.jaworskimateusz.machineapi.model.Service;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceMapper {

    public static List<ServiceDto> mapToServiceDtoList(List<Service> services) {
        return services.stream()
                .map(ServiceMapper::mapToServiceDto)
                .collect(Collectors.toList());
    }

    public static ServiceDto mapToServiceDto(Service service) {
        return new ServiceDto(
                service.getServiceId(),
                service.getDate(),
                service.getComponentName(),
                service.getDescription(),
                service.getResult(),
                service.getMachine().getMachineId()
        );
    }
}
