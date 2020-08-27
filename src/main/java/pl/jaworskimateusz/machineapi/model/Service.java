package pl.jaworskimateusz.machineapi.model;


import org.springframework.format.annotation.DateTimeFormat;
import pl.jaworskimateusz.machineapi.utils.DateUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date date;
    private String componentName;
    private String description;
    private int result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id")
    private Machine machine;

    public Service() {
    }

    public Service(Long serviceId, Date date, String componentName, String description, int result) {
        this.serviceId = serviceId;
        this.date = date;
        this.componentName = componentName;
        this.description = description;
        this.result = result;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
