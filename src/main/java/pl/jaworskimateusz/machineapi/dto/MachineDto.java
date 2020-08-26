package pl.jaworskimateusz.machineapi.dto;

public class MachineDto {

    private Long machineId;
    private String name;
    private String code;
    private String description;
    private String image;
    private String serviceInstruction;

    public MachineDto(Long machineId, String name, String code, String description, String image, String service_instruction) {
        this.machineId = machineId;
        this.name = name;
        this.code = code;
        this.description = description;
        this.image = image;
        this.serviceInstruction = service_instruction;
    }

    public Long getMachineId() {
        return machineId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getServiceInstruction() {
        return serviceInstruction;
    }
}
