package pl.jaworskimateusz.machineapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.jaworskimateusz.machineapi.dto.TaskDto;
import pl.jaworskimateusz.machineapi.dto.UserDto;
import pl.jaworskimateusz.machineapi.mapper.TaskMapper;
import pl.jaworskimateusz.machineapi.mapper.UserMapper;
import pl.jaworskimateusz.machineapi.model.Task;
import pl.jaworskimateusz.machineapi.model.User;
import pl.jaworskimateusz.machineapi.service.UserService;

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
        return UserMapper.mapToUserDtoList(userService.findAll(pageNumber));
    }

    @GetMapping("/users/{id}")
    public UserDto findUserById(@PathVariable long id) {
        return UserMapper.mapToUserDto(userService.findById(id));
    }

    @PostMapping("/users")
    public UserDto saveUser(@RequestBody User user) {
        return UserMapper.mapToUserDto(userService.saveUser(user));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteById(id);
    }

    @GetMapping("/users/{userId}/tasks")
    public List<TaskDto> findUserTasks(@PathVariable long userId) {
        return TaskMapper.mapToTaskDtoList(userService.findById(userId).getTasks());
    }

    @GetMapping("/users/{userId}/tasks/{taskId}")
    public TaskDto findUserTaskById(@PathVariable long userId, @PathVariable long taskId) {
        return TaskMapper.mapToTaskDto(userService.findUserTask(userId, taskId));
    }

    @PostMapping("/users/{userId}/tasks")
    public UserDto saveUserTask(@PathVariable long userId, @RequestBody Task task) {
        User user = userService.findById(userId);
        user.addTask(task);
        return UserMapper.mapToUserDto(userService.saveUser(user));
    }

    @DeleteMapping("/users/{userId}/tasks")
    public void deleteUserTask(@PathVariable long userId, @RequestBody Task task) {
        User user = userService.findById(userId);
        user.removeTask(task);
        userService.saveUser(user);
    }
}
