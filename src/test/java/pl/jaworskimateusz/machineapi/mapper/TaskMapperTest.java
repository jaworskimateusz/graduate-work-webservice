package pl.jaworskimateusz.machineapi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jaworskimateusz.machineapi.dto.TaskDto;
import pl.jaworskimateusz.machineapi.model.Task;

import java.util.List;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskMapperTest {

    @Test
    public void should_map_to_task_dto_list() {
        Task task1 = new Task(1L,"title", "description", new Date(), 1);
        Task task2 = new Task(2L,"task two", "new description", new Date(), 0);
        List<Task> actualTasks = Arrays.asList(task1, task2);

        List<TaskDto> expectedTaskDtoList = TaskMapper.mapToTaskDtoList(actualTasks);

        assertEquals(expectedTaskDtoList.size(), 2);
        assertEquals(expectedTaskDtoList.get(0).getTaskId(), 1L);
        assertEquals(expectedTaskDtoList.get(1).getSolved(), 0);

    }

    @Test
    public void should_map_task_to_task_dto() {
        Task actual = new Task(1L,"title", "description", new Date(), 1);

        TaskDto expected = TaskMapper.mapToTaskDto(actual);

        assertNotNull(expected);
        assertEquals(expected.getTaskId(),1L);
        assertEquals(expected.getTitle(),"title");
        assertEquals(expected.getDescription(),"description");
        assertEquals(expected.getDate(),actual.getDate());
        assertEquals(expected.getSolved(),1);

    }
}