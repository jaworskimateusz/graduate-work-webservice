package pl.jaworskimateusz.machineapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.jaworskimateusz.machineapi.dto.IssueDto;
import pl.jaworskimateusz.machineapi.dto.MachineDto;
import pl.jaworskimateusz.machineapi.dto.ServiceDto;
import pl.jaworskimateusz.machineapi.mapper.IssueMapper;
import pl.jaworskimateusz.machineapi.mapper.MachineMapper;
import pl.jaworskimateusz.machineapi.mapper.ServiceMapper;
import pl.jaworskimateusz.machineapi.model.Issue;
import pl.jaworskimateusz.machineapi.model.Machine;
import pl.jaworskimateusz.machineapi.model.Service;
import pl.jaworskimateusz.machineapi.service.MachineService;

import java.util.List;

@RestController
public class MachineController {

    private MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/machines/all")
    public List<MachineDto> findAllMachines(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        return MachineMapper.mapToMachineDtoList(machineService.findAll(pageNumber));
    }

    @GetMapping("/machines/{id}")
    public MachineDto findMachineById(@PathVariable long id) {
        return MachineMapper.mapToMachineDto(machineService.findById(id));
    }

    @GetMapping("/machines")
    public MachineDto findMachineByCode(@RequestParam String code) {
        return MachineMapper.mapToMachineDto(machineService.findMachineByCode(code));
    }

    @PostMapping("/machines")
    public MachineDto saveMachine(@RequestBody Machine machine) {
        return MachineMapper.mapToMachineDto(machineService.saveMachine(machine));
    }

    @GetMapping("/issues")
    public List<IssueDto> getAllIssues(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        return IssueMapper.mapToIssueDtoList(machineService.getAllIssues(pageNumber));
    }

    @GetMapping("/machines/{machineId}/issues")
    public List<IssueDto> findMachineIssues(@PathVariable long machineId, @RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        return IssueMapper.mapToIssueDtoList(machineService.findMachineIssues(machineId, pageNumber));
    }

    @PostMapping("/machines/{machineId}/issues")
    public IssueDto saveMachineIssue(@PathVariable long machineId, @RequestBody Issue issue) {
        return IssueMapper.mapToIssueDto(machineService.saveMachineIssue(machineId, issue));
    }

    @PutMapping("/machines/{machineId}/services")
    public ServiceDto saveMachineService(@PathVariable long machineId, @RequestBody Service service) {
        return ServiceMapper.mapToServiceDto(machineService.saveMachineService(machineId, service));
    }

}