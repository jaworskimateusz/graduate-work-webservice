package pl.jaworskimateusz.machineapi.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pl.jaworskimateusz.machineapi.exception.NotFoundException;
import pl.jaworskimateusz.machineapi.model.Machine;
import pl.jaworskimateusz.machineapi.model.Task;
import pl.jaworskimateusz.machineapi.model.User;
import pl.jaworskimateusz.machineapi.repository.MachineRepository;
import pl.jaworskimateusz.machineapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MachineRepository machineRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_list_of_users() {
        User user1 = new User(1, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);
        User user2 = new User(2, "john@gmail.com", "password123","WORKER", "JOHN", "-", 321321321, "CTO",(short)1);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Page<User> pagedUsers = new PageImpl<>(users);

        int page = 0;
        when(userRepository.findAll(PageRequest.of(page, 20))).thenReturn(pagedUsers);
        List<User> result = userService.findAllUsers(page);

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getUsername(), user1.getUsername());
        assertEquals(result.get(1).getFirstName(), "JOHN");
    }

    @Test
    public void should_return_user_by_id() {
        long userId = 1L;
        User user = new User(userId, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        assertNotNull(userService.findById(userId));
    }

    @Test
    public void should_return_user_by_username() {
        String username = "joedoe@email.com";
        User user = new User(1L, username, "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);

        when(userRepository.findByUsername(username)).thenReturn(user);

        assertNotNull(userService.findByUsername(username));
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_user_not_found_exception() {
        long userId = 1L;
        User user = new User(userId, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);

        lenient().when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        userService.findById(2L);
    }

    @Test
    public void should_save_user() {
        User user = new User(1, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals(result.getUsername(), user.getUsername());
        assertEquals("ADMIN", user.getRole());
        assertEquals(result.getUserId(), user.getUserId());
    }

    @Test(expected = Exception.class)
    public void should_not_save_user_when_the_same_username() {
        User user1 = new User(1, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);
        User user2 = new User(2, "joedoe@email.com", "password12","WORKER", "Joe", "Doe", 223123123, "Serviceman",(short)1);

        lenient().when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenThrow();
        userService.saveUser(user1);
        userService.saveUser(user2);
    }

    @Test
    public void should_save_user_task() {
        User user = new User(1L, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);
        Task task = new Task(1L,"title", "description", new Date(), 1);

        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user));
        userService.saveUserTask(1, task);

        verify(userRepository, Mockito.times(1)).findById(any());
        verify(userRepository, Mockito.times(1)).save(any());
        assertEquals(user.getTasks().size(),1);
        assertTrue(user.getTasks().contains(task));
    }

    @Test
    public void should_remove_user_task() {
        User user = new User(1, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);
        Task task1 = new Task(1L,"title", "description", new Date(), 1);
        Task task2 = new Task(2L,"title2", "description", new Date(), 0);

        user.addTask(task1);
        user.addTask(task2);

        assertEquals(user.getTasks().size(),2);

        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user));

        userService.removeUserTask(1L, task2);

        verify(userRepository, Mockito.times(1)).findById(any());

        verify(userRepository, Mockito.times(1)).save(any());

        assertEquals(user.getTasks().size(),1);
        assertTrue(user.getTasks().contains(task1));
        assertFalse(user.getTasks().contains(task2));
    }

    @Test
    public void should_save_user_machine() {
        User user = new User(1L, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);
        Machine machine = new Machine(1L, "machine1","","","","");

        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user));
        userService.saveUserMachine(1, machine);

        verify(userRepository, Mockito.times(1)).findById(any());
        verify(userRepository, Mockito.times(1)).save(any());
        assertEquals(user.getMachines().size(),1);
        assertTrue(user.getMachines().contains(machine));
    }

    @Test
    public void should_remove_user_machine_by_id() {
        User user = new User(1, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);
        Machine machine1 = new Machine(1L, "machine1","","","","");
        Machine machine2 = new Machine(2L, "machine2","","","","");

        user.addMachine(machine1);
        user.addMachine(machine2);

        assertEquals(user.getMachines().size(),2);

        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user));
        when(machineRepository.findById(any())).thenReturn(java.util.Optional.of(machine2));

        userService.removeUserMachineById(1,2);

        verify(userRepository, Mockito.times(1)).findById(any());

        verify(machineRepository, Mockito.times(1)).findById(any());

        assertEquals(user.getMachines().size(),1);
        assertTrue(user.getMachines().contains(machine1));
        assertFalse(user.getMachines().contains(machine2));
    }
}