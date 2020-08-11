package pl.jaworskimateusz.machineapi.mapper;

import pl.jaworskimateusz.machineapi.dto.UserDto;
import pl.jaworskimateusz.machineapi.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getDepartmentId(),
                user.getEnabled()
        );
    }
}
