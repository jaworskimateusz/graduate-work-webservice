package pl.jaworskimateusz.machineapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.jaworskimateusz.machineapi.exception.NotFoundException;
import pl.jaworskimateusz.machineapi.model.Machine;
import pl.jaworskimateusz.machineapi.model.Task;
import pl.jaworskimateusz.machineapi.model.User;
import pl.jaworskimateusz.machineapi.repository.MachineRepository;
import pl.jaworskimateusz.machineapi.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private MachineRepository machineRepository;

    private static final int PAGE_SIZE = 20;

    public UserService(UserRepository userRepository, MachineRepository machineRepository) {
        this.userRepository = userRepository;
        this.machineRepository = machineRepository;
    }

    public List<User> findAllUsers(int page) {
        return userRepository.findAll(PageRequest.of(page, PAGE_SIZE)).getContent();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(this.getClass().getSimpleName(), id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Task findUserTask(long userId, long taskId) {
        return this.findById(userId)
                .getTasks()
                .stream()
                .filter(t -> t.getTaskId() == taskId)
                .findFirst()
                .orElseThrow(()-> new NotFoundException(this.getClass().getSimpleName(), taskId));
    }

    public List<Task> findUserTasksAfter(Date date, Long userId) {
        return findById(userId)
                .getTasks()
                .stream()
                .filter(task -> task.getDate().after(date))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        User user = findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.authorities(user.getRole());
        } else {
            throw new NotFoundException(this.getClass().getSimpleName(), username);
        }
        return builder.build();
    }

    public Task saveUserTask(long userId, Task task) {
        User user = this.findById(userId);
        user.addTask(task);
        this.saveUser(user);
        return user.getTasks().get(user.getTasks().size() - 1);
    }

    public void removeUserTask(long userId, Task task) {
        User user = this.findById(userId);
        user.removeTask(task);
        saveUser(user);
    }

    public Machine saveUserMachine(long userId, Machine machine) {
        User user = this.findById(userId);
        user.addMachine(machine);
        this.saveUser(user);
        return user.getMachines().get(user.getMachines().size() - 1);
    }

    public void removeUserMachineById(long userId, long machineId) {
        User user = this.findById(userId);
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(()-> new NotFoundException(this.getClass().getSimpleName(), machineId));
        user.removeMachine(machine);
        this.saveUser(user);
    }

}
