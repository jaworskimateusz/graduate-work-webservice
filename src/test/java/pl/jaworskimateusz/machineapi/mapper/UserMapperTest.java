package pl.jaworskimateusz.machineapi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jaworskimateusz.machineapi.dto.UserDto;
import pl.jaworskimateusz.machineapi.model.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {

    @Test
    public void should_map_to_user_dto_list() {
        User user1 = new User(1L, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);
        User user2 = new User(2L, "maxdoe@email.com", "password123","WORKER", "Max", "Doe", 223113123, "CTO",(short)1);
        List<User> actualUsers = Arrays.asList(user1, user2);

        List<UserDto> expectedUserDtoList = UserMapper.mapToUserDtoList(actualUsers);

        assertEquals(expectedUserDtoList.size(), 2);
        assertEquals(expectedUserDtoList.get(0).getUsername(), "joedoe@email.com");
        assertEquals(expectedUserDtoList.get(1).getRole(), "WORKER");
    }

    @Test
    public void should_map_user_to_use_dto() {
        User actualUser = new User(1L, "joedoe@email.com", "password","ADMIN", "Joe", "Doe", 123123123, "Service",(short)1);

        UserDto expectedUserDto = UserMapper.mapToUserDto(actualUser);

        assertNotNull(expectedUserDto);
        assertEquals(expectedUserDto.getUserId(), 1L);
        assertEquals(expectedUserDto.getUsername(),"joedoe@email.com");
        assertEquals(expectedUserDto.getPassword(),"password");
        assertEquals(expectedUserDto.getRole(),"ADMIN");
        assertEquals(expectedUserDto.getFirstName(),"Joe");
        assertEquals(expectedUserDto.getLastName(), "Doe");
        assertEquals(expectedUserDto.getPhoneNumber(),123123123);
        assertEquals(expectedUserDto.getDepartment(),"Service");
        assertEquals(expectedUserDto.getEnabled(),(short)1);
    }
}