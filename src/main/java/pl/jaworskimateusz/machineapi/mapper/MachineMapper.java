package pl.jaworskimateusz.machineapi.mapper;

import pl.jaworskimateusz.machineapi.dto.MachineDto;
import pl.jaworskimateusz.machineapi.model.Machine;

import java.util.List;
import java.util.stream.Collectors;

public class MachineMapper {

    public static List<MachineDto> mapToMachineDtoList(List<Machine> machines) {
        return machines.stream()
                .map(MachineMapper::mapToMachineDto)
                .collect(Collectors.toList());
    }

    public static MachineDto mapToMachineDto(Machine machine) {
        return new MachineDto(
                machine.getMachineId(),
                machine.getName(),
                machine.getCode(),
                machine.getDescription(),
                machine.getImage(),
                machine.getServiceInstruction()
        );
    }
}
