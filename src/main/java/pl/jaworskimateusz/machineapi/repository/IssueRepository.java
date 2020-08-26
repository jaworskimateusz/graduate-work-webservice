package pl.jaworskimateusz.machineapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jaworskimateusz.machineapi.model.Issue;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i WHERE i.machine.machineId = :machineId")
    List<Issue> findIssuesByMachineId(Pageable page, @Param("machineId") long machineId);

}


