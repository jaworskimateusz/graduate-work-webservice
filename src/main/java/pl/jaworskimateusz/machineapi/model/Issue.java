package pl.jaworskimateusz.machineapi.model;


import javax.persistence.*;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;
    private String keywords;
    private String description;
    private String solution;
    private String workerSignature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id")
    private Machine machine;

    public Issue(Long issueId, String keywords, String description, String solution, String workerSignature) {
        this.issueId = issueId;
        this.keywords = keywords;
        this.description = description;
        this.solution = solution;
        this.workerSignature = workerSignature;
    }

    public Issue() {
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getWorkerSignature() {
        return workerSignature;
    }

    public void setWorkerSignature(String workerSignature) {
        this.workerSignature = workerSignature;
    }

}
