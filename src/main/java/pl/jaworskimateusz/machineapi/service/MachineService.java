package pl.jaworskimateusz.machineapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.jaworskimateusz.machineapi.exception.NotFoundException;
import pl.jaworskimateusz.machineapi.model.Issue;
import pl.jaworskimateusz.machineapi.model.Machine;
import pl.jaworskimateusz.machineapi.repository.IssueRepository;
import pl.jaworskimateusz.machineapi.repository.MachineRepository;

import java.util.List;

@Service
public class MachineService {

    private MachineRepository machineRepository;
    private IssueRepository issueRepository;

    private static final int PAGE_SIZE = 20;

    public MachineService(MachineRepository machineRepository, IssueRepository issueRepository) {
        this.machineRepository = machineRepository;
        this.issueRepository = issueRepository;
    }

    public List<Machine> findAll(int page) {
        return machineRepository.findAll(PageRequest.of(page, PAGE_SIZE)).getContent();
    }

    public Machine findById(long id) {
        return machineRepository.findById(id).orElseThrow(()-> new NotFoundException(this.getClass().getSimpleName(), id));
    }

    public Machine saveMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public void deleteById(long id) {
        Machine machine = this.findById(id);
        machineRepository.delete(machine);
    }

    public Machine findMachineByCode(String code) {
        Machine machine = machineRepository.findByCode(code);
        if (machine == null)
            throw new NotFoundException(this.getClass().getSimpleName(), code, false);
        return machine;
    }

    public Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public List<Issue> findMachineIssues(long machineId, int pageNumber) {
        return issueRepository.findIssuesByMachineId(PageRequest.of(pageNumber, PAGE_SIZE), machineId);
    }

    public List<Issue> getAllIssues(int pageNumber) {
        return issueRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE)).getContent();
    }
}
