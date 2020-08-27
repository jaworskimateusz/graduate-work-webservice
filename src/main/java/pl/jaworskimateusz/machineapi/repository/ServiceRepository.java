package pl.jaworskimateusz.machineapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jaworskimateusz.machineapi.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}


