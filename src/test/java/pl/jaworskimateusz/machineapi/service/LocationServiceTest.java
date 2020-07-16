package pl.jaworskimateusz.machineapi.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.jaworskimateusz.machineapi.model.Location;
import pl.jaworskimateusz.machineapi.repository.LocationRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Before
    public void setup() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void saveLocation() {
    }

    @Test
    public void deleteById() {
    }

    private List<Location> getLocationsAsList() {
        return Arrays.asList(
                new Location("00-014 Świętokrzyska","Warszawa","PL"),
                new Location("00-014 Złota","Warszawa","PL"),
                new Location("04-218 Długa","Kraków","PL")
        );
    }
}