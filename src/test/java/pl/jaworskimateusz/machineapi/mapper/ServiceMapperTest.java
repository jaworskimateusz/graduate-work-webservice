package pl.jaworskimateusz.machineapi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jaworskimateusz.machineapi.dto.ServiceDto;
import pl.jaworskimateusz.machineapi.model.Machine;
import pl.jaworskimateusz.machineapi.model.Service;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceMapperTest {

    @Test
    public void should_map_service_to_service_dto_without_machine() {
        Service actualService = new Service(1L, new Date(),"cpu","description",1);

        ServiceDto expectedServiceDto = ServiceMapper.mapToServiceDto(actualService);

        assertNotNull(expectedServiceDto);
        assertEquals(expectedServiceDto.getServiceId(),1L);
        assertEquals(expectedServiceDto.getDate(),actualService.getDate());
        assertEquals(expectedServiceDto.getComponentName(),"cpu");
        assertEquals(expectedServiceDto.getDescription(),"description");
        assertEquals(expectedServiceDto.getResult(),1);
        assertNull(expectedServiceDto.getMachineId());

    }

    @Test
    public void should_map_service_to_service_dto_with_machine() {
        Service actualService = new Service(1L, new Date(),"cpu","description",1);
        Machine machine = new Machine(1L, "machine1","","","","");
        actualService.setMachine(machine);

        ServiceDto expectedServiceDto = ServiceMapper.mapToServiceDto(actualService);

        assertNotNull(expectedServiceDto);
        assertEquals(expectedServiceDto.getServiceId(),1L);
        assertEquals(expectedServiceDto.getDate(),actualService.getDate());
        assertEquals(expectedServiceDto.getComponentName(),"cpu");
        assertEquals(expectedServiceDto.getDescription(),"description");
        assertEquals(expectedServiceDto.getResult(),1);
        assertEquals(expectedServiceDto.getMachineId(),1L);

    }
}