package pl.jaworskimateusz.machineapi.dto;

public class ApplicationProblemDto {

    private Long applicationProblemId;
    private String keywords;
    private String description;
    private String versionCode;
    private Long userId;

    public ApplicationProblemDto(Long applicationProblemId, String keywords, String description, String versionCode, Long userId) {
        this.applicationProblemId = applicationProblemId;
        this.keywords = keywords;
        this.description = description;
        this.versionCode = versionCode;
        this.userId = userId;
    }

    public Long getApplicationProblemId() {
        return applicationProblemId;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getDescription() {
        return description;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public Long getUserId() {
        return userId;
    }
}
