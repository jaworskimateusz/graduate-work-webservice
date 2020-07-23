package pl.jaworskimateusz.machineapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jaworskimateusz.machineapi.model.ApplicationProblem;
import pl.jaworskimateusz.machineapi.model.Location;

@Repository
public interface ApplicationProblemsRepository extends JpaRepository<ApplicationProblem, Long> {

}


