package pl.jaworskimateusz.machineapi.model;

import javax.persistence.*;

@Entity
@Table(name = "aplication_problems") //TODO repair db
public class ApplicationProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationProblemId;
    private String keywords;
    private String description;

    @Column(name = "employee_id") //TODO
    private Long userId;

    public ApplicationProblem() {
    }

    public Long getApplicationProblemId() {
        return applicationProblemId;
    }

    public void setApplicationProblemId(Long applicationProblemId) {
        this.applicationProblemId = applicationProblemId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
