package pl.jaworskimateusz.machineapi.dto;

public class UserDto {

    private long userId;
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String department;
    private short enabled;

    public UserDto(long userId, String username, String password, String role, String firstName, String lastName, int phoneNumber, String department, short enabled) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.enabled = enabled;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public short getEnabled() {
        return enabled;
    }
}
