package pl.jaworskimateusz.machineapi.dto;

public class IssueDto {

    private Long issueId;
    private String keywords;
    private String description;
    private String solution;
    private String workerSignature;
    private Long machineId;

    public IssueDto(Long issueId, String keywords, String description, String solution, String workerSignature, Long machineId) {
        this.issueId = issueId;
        this.keywords = keywords;
        this.description = description;
        this.solution = solution;
        this.workerSignature = workerSignature;
        this.machineId = machineId;
    }

    public Long getIssueId() {
        return issueId;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getDescription() {
        return description;
    }

    public String getSolution() {
        return solution;
    }

    public String getWorkerSignature() {
        return workerSignature;
    }

    public Long getMachineId() {
        return machineId;
    }
}
