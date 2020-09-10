package pl.jaworskimateusz.machineapi.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pl.jaworskimateusz.machineapi.exception.NotFoundException;
import pl.jaworskimateusz.machineapi.model.Issue;
import pl.jaworskimateusz.machineapi.model.Machine;
import pl.jaworskimateusz.machineapi.model.Service;
import pl.jaworskimateusz.machineapi.repository.IssueRepository;
import pl.jaworskimateusz.machineapi.repository.MachineRepository;
import pl.jaworskimateusz.machineapi.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class MachineServiceTest {

    @Mock
    private MachineRepository machineRepository;

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private MachineService machineService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_list_of_machines() {
        Machine machine1 = new Machine(1L, "machine1","","","","");
        Machine machine2 = new Machine(2L, "Arduino","","","","");
        List<Machine> machines = new ArrayList<>();
        machines.add(machine1);
        machines.add(machine2);
        Page<Machine> pagedMachines = new PageImpl<>(machines);

        int page = 0;
        when(machineRepository.findAll(PageRequest.of(page, 20))).thenReturn(pagedMachines);
        List<Machine> result = machineService.findAll(page);

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), machine1.getName());
        assertEquals(result.get(1).getName(), "Arduino");
    }

    @Test
    public void should_return_machine_by_id() {
        long machineId = 1L;
        Machine machine = new Machine(machineId, "Arduino","","","","");

        when(machineRepository.findById(machineId)).thenReturn(java.util.Optional.of(machine));

        assertNotNull(machineService.findById(machineId));
    }

    @Test
    public void should_return_machine_by_code() {
        String code = "CODE1247KW";
        Machine machine = new Machine(1L, "Arduino",code,"","","");

        when(machineRepository.findByCode(code)).thenReturn(machine);

        assertNotNull(machineService.findMachineByCode(code));
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_machine_not_found_exception() {
        long machineId = 1L;
        Machine machine = new Machine(1L, "Arduino","code","","","");

        lenient().when(machineRepository.findById(machineId)).thenReturn(java.util.Optional.of(machine));

        machineService.findById(2L);
    }

    @Test
    public void should_save_machine() {
        Machine machine = new Machine(1L, "Arduino","code","","","");

        when(machineRepository.save(machine)).thenReturn(machine);

        Machine result = machineService.saveMachine(machine);

        assertNotNull(result);
        assertEquals(result.getName(), machine.getName());
        assertEquals(result.getCode(), "code");
    }

    @Test
    public void should_save_issue() {
        Issue issue = new Issue(1L, "error","description","solution","sign");

        when(issueRepository.save(issue)).thenReturn(issue);

        Issue result = machineService.saveIssue(issue);

        assertNotNull(result);
        assertEquals(result.getKeywords(), issue.getKeywords());
        assertEquals(result.getDescription(), "description");
    }

    @Test
    public void should_return_list_of_issues() {
        List<Issue> issues = new ArrayList<>();
        Issue issue1 = new Issue(1L, "error","description","solution","sign");
        Issue issue2 = new Issue(2L, "cpu","description 2","-","-");

        issues.add(issue1);
        issues.add(issue2);
        Page<Issue> pagedIssues = new PageImpl<>(issues);

        int page = 0;
        when(issueRepository.findAll(PageRequest.of(page, 20))).thenReturn(pagedIssues);
        List<Issue> result = machineService.getAllIssues(page);

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getKeywords(), issue1.getKeywords());
        assertEquals(result.get(1).getSolution(), "-");
    }

    @Test
    public void should_return_list_of_machine_issues() {
        long machineId = 1;
        Machine machine = new Machine(machineId, "machine1","","","","");
        Issue issue1 = new Issue(1L, "error","description","solution","sign");
        Issue issue2 = new Issue(2L, "cpu","description 2","-","-");
        machine.addIssue(issue1);
        machine.addIssue(issue2);

        int page = 0;
        when(issueRepository.findIssuesByMachineId(PageRequest.of(page, 20), machineId)).thenReturn(machine.getIssues());
        when(issueRepository.findIssuesByMachineId(PageRequest.of(page, 20), 2L)).thenReturn(new ArrayList<>());

        List<Issue> result1 = machineService.findMachineIssues(machineId, page);
        List<Issue> result2 = machineService.findMachineIssues(2L, page);

        assertEquals(result1.size(), 2);
        assertEquals(result2.size(), 0);
        assertEquals(result1.get(0).getKeywords(), issue1.getKeywords());
    }

    @Test
    public void should_save_service() {
        Service service = new Service(1L, new Date(),"cpu","description",1);

        when(serviceRepository.save(service)).thenReturn(service);

        Service result = machineService.saveService(service);

        assertNotNull(result);
        assertEquals(result.getComponentName(), service.getComponentName());
        assertEquals(result.getDescription(), "description");
    }

    @Test
    public void should_save_machine_service() {
        Machine machine = new Machine(1L, "machine1","","","","");
        Service service = new Service(1L, new Date(),"cpu","description",1);

        when(machineRepository.findById(any())).thenReturn(java.util.Optional.of(machine));
        machineService.saveMachineService(1, service);

        verify(machineRepository, Mockito.times(1)).findById(any());
        verify(machineRepository, Mockito.times(1)).save(any());
        assertEquals(machine.getServices().size(),1);
        assertTrue(machine.getServices().contains(service));
    }

    @Test
    public void should_save_machine_issue() {
        Machine machine = new Machine(1L, "machine1","","","","");
        Issue issue = new Issue(1L, "error","description","solution","sign");

        when(machineRepository.findById(any())).thenReturn(java.util.Optional.of(machine));
        machineService.saveMachineIssue(1, issue);

        verify(machineRepository, Mockito.times(1)).findById(any());
        verify(machineRepository, Mockito.times(1)).save(any());
        assertEquals(machine.getIssues().size(),1);
        assertTrue(machine.getIssues().contains(issue));
    }

}