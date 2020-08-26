package pl.jaworskimateusz.machineapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.jaworskimateusz.machineapi.exception.NotFoundException;
import pl.jaworskimateusz.machineapi.model.ApplicationProblem;
import pl.jaworskimateusz.machineapi.repository.ApplicationProblemsRepository;

import java.util.List;

@Service
public class ApplicationProblemsService {

    private ApplicationProblemsRepository applicationProblemsRepository;

    private static final int PAGE_SIZE = 20;

    public ApplicationProblemsService(ApplicationProblemsRepository applicationProblemsRepository) {
        this.applicationProblemsRepository = applicationProblemsRepository;
    }

    public List<ApplicationProblem> findAll(int page) {
        return applicationProblemsRepository.findAll(PageRequest.of(page, PAGE_SIZE)).getContent();
    }

    public ApplicationProblem findById(long id) {
        return applicationProblemsRepository.findById(id).orElseThrow(()-> new NotFoundException(this.getClass().getSimpleName(), id));
    }

    public ApplicationProblem save(ApplicationProblem applicationProblem) {
        return applicationProblemsRepository.save(applicationProblem);
    }

    public void deleteById(long id) {
        ApplicationProblem applicationProblem = this.findById(id);
        applicationProblemsRepository.delete(applicationProblem);
    }
}
