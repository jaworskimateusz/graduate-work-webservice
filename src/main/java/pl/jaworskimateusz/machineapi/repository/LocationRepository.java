package pl.jaworskimateusz.machineapi.repository;

import pl.jaworskimateusz.machineapi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}


