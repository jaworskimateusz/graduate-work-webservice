package pl.jaworskimateusz.machineapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jaworskimateusz.machineapi.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}


