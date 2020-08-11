package pl.jaworskimateusz.machineapi.dto;

import java.util.Date;

public class TaskDto {

    private Long taskId;
    private String title;
    private String description;
    private Date date;
    private int solved;

    public TaskDto(Long taskId, String title, String description, Date date, int solved) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.solved = solved;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int getSolved() {
        return solved;
    }
}
