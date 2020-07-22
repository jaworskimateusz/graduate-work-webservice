package pl.jaworskimateusz.machineapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.jaworskimateusz.machineapi.exception.NotFoundException;
import pl.jaworskimateusz.machineapi.model.User;
import pl.jaworskimateusz.machineapi.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private static final int PAGE_SIZE = 20;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public void deleteById(long id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        User user = findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(user.getRole());
        } else {
            throw new NotFoundException(this.getClass().getSimpleName(), username);
        }
        return builder.build();
    }

}
