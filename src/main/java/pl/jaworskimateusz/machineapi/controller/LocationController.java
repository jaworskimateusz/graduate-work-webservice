package pl.jaworskimateusz.machineapi.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import pl.jaworskimateusz.machineapi.model.Location;
import pl.jaworskimateusz.machineapi.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public List<Location> findAllLocations(@RequestParam(required = false) Integer page,
                                           @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        return locationService.findAll(pageNumber);
    }

    @GetMapping("/locations/{id}")
    public Location findLocationById(@PathVariable long id) {
        return locationService.findById(id);
    }

    @PostMapping("/locations")
    public Location saveLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocationById(@PathVariable long id) {
        locationService.deleteById(id);
    }
}
