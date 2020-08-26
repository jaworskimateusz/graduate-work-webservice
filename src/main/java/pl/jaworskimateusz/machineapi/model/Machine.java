package pl.jaworskimateusz.machineapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "machines")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machineId;
    private Long departmentId; //TODO delete
    private String name;
    private String code;
    private String description;
    private String image;
    private String serviceInstruction;

    @OneToMany(mappedBy = "machine")
    private List<Issue> issues = new ArrayList<>();

    public Machine() {
    }

    public Machine(Long machineId, Long departmentId, String name, String code, String description, String image, String serviceInstruction) {
        this.machineId = machineId;
        this.departmentId = departmentId;
        this.name = name;
        this.code = code;
        this.description = description;
        this.image = image;
        this.serviceInstruction = serviceInstruction;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getServiceInstruction() {
        return serviceInstruction;
    }

    public void setServiceInstruction(String serviceInstruction) {
        this.serviceInstruction = serviceInstruction;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public void addIssue(Issue issue) {
        ifExists(issue);
        issues.add(issue);
        issue.setMachine(this);
    }

    private void ifExists(Issue issue) {
        for (Issue i : issues) {
            if (i.getIssueId().equals(issue.getIssueId())) {
                removeIssue(i);
                break;
            }
        }
    }

    public void removeIssue(Issue issue) {
        issues.remove(issue);
        issue.setMachine(null);
    }
}
