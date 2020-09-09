package pl.jaworskimateusz.machineapi.model;

import org.springframework.format.annotation.DateTimeFormat;
import pl.jaworskimateusz.machineapi.utils.DateUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;
    private String description;
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date date;
    private int solved;

    public Task() {
    }

    public Task(Long taskId, String title, String description, Date date, int solved) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.solved = solved;
    }

    @ManyToMany(mappedBy = "tasks")
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }
}
