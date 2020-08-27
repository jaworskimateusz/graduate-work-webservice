package pl.jaworskimateusz.machineapi.dto;

import java.util.Date;

public class ServiceDto {

    private Long serviceId;
    private Date date;
    private String componentName;
    private String description;
    private int result;
    private Long machineId;

    public ServiceDto(Long serviceId, Date date, String componentName, String description, int result, Long machineId) {
        this.serviceId = serviceId;
        this.date = date;
        this.componentName = componentName;
        this.description = description;
        this.result = result;
        this.machineId = machineId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public Date getDate() {
        return date;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getDescription() {
        return description;
    }

    public int getResult() {
        return result;
    }

    public Long getMachineId() {
        return machineId;
    }
}
