package pl.jaworskimateusz.machineapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.jaworskimateusz.machineapi.model.Location;
import pl.jaworskimateusz.machineapi.model.User;
import pl.jaworskimateusz.machineapi.service.LocationService;
import pl.jaworskimateusz.machineapi.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAllLocations(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        return userService.findAll(pageNumber);
    }

    @GetMapping("/users/{id}")
    public User findLocationById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteById(id);
    }
}
