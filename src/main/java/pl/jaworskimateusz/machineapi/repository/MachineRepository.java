package pl.jaworskimateusz.machineapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jaworskimateusz.machineapi.model.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    Machine findByCode(String code);

}


