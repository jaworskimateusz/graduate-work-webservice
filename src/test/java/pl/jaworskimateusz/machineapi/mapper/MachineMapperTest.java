package pl.jaworskimateusz.machineapi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jaworskimateusz.machineapi.dto.MachineDto;
import pl.jaworskimateusz.machineapi.model.Machine;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MachineMapperTest {

    @Test
    public void should_map_to_machine_dto_list() {
        Machine machine1 = new Machine(1L, "Arduino","CODE12","description","image_url","-");
        Machine machine2 = new Machine(2L, "CPU","CODE92","description","image_url","-");
        List<Machine> actualMachines = Arrays.asList(machine1, machine2);

        List<MachineDto> expectedMachineDtoList = MachineMapper.mapToMachineDtoList(actualMachines);

        assertEquals(expectedMachineDtoList.size(), 2);
        assertEquals(expectedMachineDtoList.get(0).getName(), "Arduino");
        assertEquals(expectedMachineDtoList.get(1).getCode(), "CODE92");
    }

    @Test
    public void should_map_machine_to_machine_dto() {
        Machine actualMachine = new Machine(1L, "Arduino","CODE12","description","image_url","");

        MachineDto expectedMachineDto = MachineMapper.mapToMachineDto(actualMachine);

        assertNotNull(expectedMachineDto);
        assertEquals(expectedMachineDto.getMachineId(),1L);
        assertEquals(expectedMachineDto.getName(),"Arduino");
        assertEquals(expectedMachineDto.getCode(),"CODE12");
        assertEquals(expectedMachineDto.getDescription(),"description");
        assertEquals(expectedMachineDto.getImage(),"image_url");
        assertEquals(expectedMachineDto.getServiceInstruction(),"");
    }
}