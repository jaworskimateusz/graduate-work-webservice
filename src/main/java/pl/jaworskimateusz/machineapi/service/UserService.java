package pl.jaworskimateusz.machineapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.jaworskimateusz.machineapi.dto.TaskDto;
import pl.jaworskimateusz.machineapi.exception.NotFoundException;
import pl.jaworskimateusz.machineapi.model.Task;
import pl.jaworskimateusz.machineapi.model.User;
import pl.jaworskimateusz.machineapi.repository.TaskRepository;
import pl.jaworskimateusz.machineapi.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;

    private static final int PAGE_SIZE = 20;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<User> findAll(int page) {
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

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(long id) {
        User user = this.findById(id);
        userRepository.delete(user);
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

}
