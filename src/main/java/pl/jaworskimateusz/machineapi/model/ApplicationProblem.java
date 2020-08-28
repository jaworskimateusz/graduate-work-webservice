package pl.jaworskimateusz.machineapi.model;

import javax.persistence.*;

@Entity
@Table(name = "application_problems")
public class ApplicationProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationProblemId;
    private String keywords;
    private String description;
    private String versionCode;
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

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
