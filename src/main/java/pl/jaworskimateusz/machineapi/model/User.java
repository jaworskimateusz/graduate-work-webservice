package pl.jaworskimateusz.machineapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String department;
    private short enabled;

    @ManyToMany(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(name = "users_tasks",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "task_id") })
    List<Task> tasks = new ArrayList<>();

    @ManyToMany(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(name = "users_machines",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "machine_id") })
    List<Machine> machines = new ArrayList<>();

    public User() {
    }

    public void addTask(Task task) {
        ifExists(task);
        tasks.add(task);
        task.getUsers().add(this);
    }

    public void removeTask(Task task) {
        tasks.removeIf(t -> t.getTaskId().equals(task.getTaskId()));
        task.getUsers().remove(this);
    }

    public void addMachine(Machine machine) {
        ifExists(machine);
        machines.add(machine);
        machine.getUsers().add(this);
    }

    public void removeMachine(Machine machine) {
        machines.removeIf(m -> m.getMachineId().equals(machine.getMachineId()));
        machine.getUsers().remove(this);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    private void ifExists(Task task) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(task.getTaskId())) {
                removeTask(t);
                break;
            }
        }
    }

    private void ifExists(Machine machine) {
        for (Machine m : machines) {
            if (m.getMachineId().equals(machine.getMachineId())) {
                removeMachine(m);
                break;
            }
        }
    }
}
