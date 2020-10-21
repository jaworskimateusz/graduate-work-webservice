package pl.jaworskimateusz.machineapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.jaworskimateusz.machineapi.dto.ApplicationProblemDto;
import pl.jaworskimateusz.machineapi.mapper.ApplicationProblemsMapper;
import pl.jaworskimateusz.machineapi.model.ApplicationProblem;
import pl.jaworskimateusz.machineapi.service.ApplicationProblemsService;

import java.util.List;

@RestController
public class ApplicationProblemsController {

    private ApplicationProblemsService applicationProblemsService;

    public ApplicationProblemsController(ApplicationProblemsService applicationProblemsService) {
        this.applicationProblemsService = applicationProblemsService;
    }

    @GetMapping("/application-problems")
    public List<ApplicationProblemDto> findAllApplicationProblems(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        return ApplicationProblemsMapper.mapToApplicationProblemDtoList(applicationProblemsService.findAll(pageNumber));
    }

    @GetMapping("/application-problems/{id}")
    public ApplicationProblemDto findApplicationProblemById(@PathVariable long id) {
        return ApplicationProblemsMapper.mapToApplicationProblemDto(applicationProblemsService.findById(id));
    }

    @PostMapping("/application-problems")
    public ApplicationProblemDto saveApplicationProblem(@RequestBody ApplicationProblem applicationProblem) {
        return ApplicationProblemsMapper.mapToApplicationProblemDto(applicationProblemsService.save(applicationProblem));
    }

    @DeleteMapping("/application-problems/{id}")
    public void deleteApplicationProblemById(@PathVariable long id) {
        applicationProblemsService.deleteById(id);
    }
}
