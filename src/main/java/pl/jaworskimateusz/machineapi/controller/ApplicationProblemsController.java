package pl.jaworskimateusz.machineapi.controller;

import org.springframework.web.bind.annotation.*;
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
    public List<ApplicationProblem> findAllApplicationProblems(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        return applicationProblemsService.findAll(pageNumber);
    }

    @GetMapping("/application-problems/{id}")
    public ApplicationProblem findApplicationProblemById(@PathVariable long id) {
        return applicationProblemsService.findById(id);
    }

    @PostMapping("/application-problems")
    public ApplicationProblem saveLocation(@RequestBody ApplicationProblem applicationProblem) {
        return applicationProblemsService.save(applicationProblem);
    }

    @DeleteMapping("/application-problems/{id}")
    public void deleteApplicationProblemById(@PathVariable long id) {
        applicationProblemsService.deleteById(id);
    }
}
