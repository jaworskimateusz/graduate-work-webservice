package pl.jaworskimateusz.machineapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.jaworskimateusz.machineapi.dto.MachineDto;
import pl.jaworskimateusz.machineapi.dto.TaskDto;
import pl.jaworskimateusz.machineapi.dto.UserDto;
import pl.jaworskimateusz.machineapi.mapper.MachineMapper;
import pl.jaworskimateusz.machineapi.mapper.TaskMapper;
import pl.jaworskimateusz.machineapi.mapper.UserMapper;
import pl.jaworskimateusz.machineapi.model.Machine;
import pl.jaworskimateusz.machineapi.model.Task;
import pl.jaworskimateusz.machineapi.model.User;
import pl.jaworskimateusz.machineapi.service.UserService;
import pl.jaworskimateusz.machineapi.utils.DateUtils;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> findAllUsers(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        return UserMapper.mapToUserDtoList(userService.findAllUsers(pageNumber));
    }

    @GetMapping("/users/{id}")
    public UserDto findUserById(@PathVariable long id) {
        return UserMapper.mapToUserDto(userService.findById(id));
    }

    @PostMapping("/users")
    public UserDto saveUser(@RequestBody User user) {
        return UserMapper.mapToUserDto(userService.saveUser(user));
    }

    @GetMapping("/users/{userId}/tasks")
    public List<TaskDto> findAllUserTasks(@PathVariable long userId, @RequestParam(required = false) String date) {
        if (date == null || date.equals("")) {
            return TaskMapper.mapToTaskDtoList(userService.findById(userId).getTasks());
        }
        Date startDate = DateUtils.stringToDate(date);
        return TaskMapper.mapToTaskDtoList(userService.findUserTasksAfter(startDate, userId));
    }

    @GetMapping("/users/{userId}/tasks/{taskId}")
    public TaskDto findUserTaskById(@PathVariable long userId, @PathVariable long taskId) {
        return TaskMapper.mapToTaskDto(userService.findUserTask(userId, taskId));
    }

    @PostMapping("/users/{userId}/tasks")
    public TaskDto saveUserTask(@PathVariable long userId, @RequestBody Task task) {
        return TaskMapper.mapToTaskDto(userService.saveUserTask(userId, task));
    }

    @DeleteMapping("/users/{userId}/tasks")
    public void removeUserTask(@PathVariable long userId, @RequestBody Task task) {
        userService.removeUserTask(userId, task);
    }

    @GetMapping("/users/{userId}/machines")
    public List<MachineDto> findAllUserMachines(@PathVariable long userId) {
        return MachineMapper.mapToMachineDtoList(userService.findById(userId).getMachines());
    }

    @PostMapping("/users/{userId}/machines")
    public MachineDto saveUserMachine(@PathVariable long userId, @RequestBody Machine machine) {
        return MachineMapper.mapToMachineDto(userService.saveUserMachine(userId, machine));
    }

    @DeleteMapping("/users/{userId}/machines/{machineId}")
    public void removeUserMachineById(@PathVariable long userId, @PathVariable long machineId) {
        userService.removeUserMachineById(userId, machineId);
    }

}
