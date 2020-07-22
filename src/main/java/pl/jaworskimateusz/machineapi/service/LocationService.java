package pl.jaworskimateusz.machineapi.service;

import org.springframework.data.domain.PageRequest;
import pl.jaworskimateusz.machineapi.exception.NotFoundException;
import pl.jaworskimateusz.machineapi.model.Location;
import pl.jaworskimateusz.machineapi.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private LocationRepository locationRepository;
    
    private static final int PAGE_SIZE = 20;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll(int page) {
        return locationRepository.findAll(PageRequest.of(page, PAGE_SIZE)).getContent();
    }

    public Location findById(long id) {
        return locationRepository.findById(id).orElseThrow(()-> new NotFoundException(this.getClass().getSimpleName(), id));
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteById(long id) {
        Location location = this.findById(id);
        locationRepository.delete(location);
    }
}
