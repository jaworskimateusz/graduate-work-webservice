package pl.jaworskimateusz.machineapi.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jaworskimateusz.machineapi.model.ApplicationProblem;
import pl.jaworskimateusz.machineapi.repository.ApplicationProblemsRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationProblemsServiceTest {

    @Mock
    private ApplicationProblemsRepository applicationProblemsRepository;

    @InjectMocks
    private ApplicationProblemsService applicationProblemsService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_list_of_application_problems() {
        ApplicationProblem  actualAppProblem1 =
                new ApplicationProblem(1L, "error","","v01",1L);
        ApplicationProblem  actualAppProblem2 =
                new ApplicationProblem(2L, "slow work","","v02",1L);
        List<ApplicationProblem> actualAppProblems = Arrays.asList(actualAppProblem1, actualAppProblem2);
        Page<ApplicationProblem> pagedProblems = new PageImpl<>(actualAppProblems);

        int page = 0;
        when(applicationProblemsRepository.findAll(PageRequest.of(page, 20))).thenReturn(pagedProblems);
        List<ApplicationProblem> expectedAppProblems = applicationProblemsService.findAll(page);

        verify(applicationProblemsRepository, Mockito.times(1)).findAll(PageRequest.of(page, 20));
        assertEquals(expectedAppProblems.size(), 2);
        assertEquals(expectedAppProblems.get(0).getApplicationProblemId(), actualAppProblem1.getApplicationProblemId());
        assertEquals(expectedAppProblems.get(1).getKeywords(), "slow work");
    }

    @Test
    public void should_find_application_problem_by_id() {
        long appProblemId = 1L;
        ApplicationProblem  actualAppProblem =
                new ApplicationProblem(appProblemId, "","","",1L);

        when(applicationProblemsRepository.findById(appProblemId)).thenReturn(java.util.Optional.of(actualAppProblem));

        assertNotNull(applicationProblemsService.findById(appProblemId));
        verify(applicationProblemsRepository, times(1)).findById(any());
    }

    @Test
    public void should_save_application_problem() {
        ApplicationProblem  actualAppProblem =
                new ApplicationProblem(1L, "","","",1L);

        when(applicationProblemsRepository.save(actualAppProblem)).thenReturn(actualAppProblem);

        ApplicationProblem expectedAppProblem = applicationProblemsService.save(actualAppProblem);

        verify(applicationProblemsRepository, Mockito.times(1)).save(any());

        assertNotNull(expectedAppProblem);
        assertEquals(expectedAppProblem.getKeywords(), actualAppProblem.getKeywords());
    }

    @Test
    public void should_delete_application_problem_by_id() {
        long appProblemId = 1L;
        ApplicationProblem  actualAppProblem =
                new ApplicationProblem(appProblemId, "","","",1L);

        when(applicationProblemsRepository.findById(appProblemId)).thenReturn(java.util.Optional.of(actualAppProblem));

        applicationProblemsService.deleteById(appProblemId);

        verify(applicationProblemsRepository,times(1)).findById(appProblemId);
        verify(applicationProblemsRepository,times(1)).delete(actualAppProblem);
    }
}