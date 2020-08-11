package pl.jaworskimateusz.machineapi.mapper;

import pl.jaworskimateusz.machineapi.dto.TaskDto;
import pl.jaworskimateusz.machineapi.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    public static List<TaskDto> mapToTaskDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::mapToTaskDto)
                .collect(Collectors.toList());
    }

    public static TaskDto mapToTaskDto(Task task) {
        return new TaskDto(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDate(),
                task.getSolved()
        );
    }
}
