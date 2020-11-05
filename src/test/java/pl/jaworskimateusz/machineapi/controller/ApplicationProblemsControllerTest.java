package pl.jaworskimateusz.machineapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.jaworskimateusz.machineapi.model.ApplicationProblem;
import pl.jaworskimateusz.machineapi.service.ApplicationProblemsService;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class ApplicationProblemsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationProblemsService applicationProblemsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void should_return_all_application_problems() throws Exception {
        // given
        ApplicationProblem applicationProblem = new ApplicationProblem(
                3L,"keywords", "description","v1.01", 1L);
        applicationProblemsService.save(applicationProblem);
        // when
        mockMvc.perform(get("/application-problems"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].applicationProblemId", Matchers.is(3)))
                .andExpect(jsonPath("$[0].keywords", Matchers.is("keywords")))
                .andExpect(jsonPath("$[0].description", Matchers.is("description")))
                .andExpect(jsonPath("$[0].versionCode", Matchers.is("v1.01")))
                .andExpect(jsonPath("$[0].userId", Matchers.is(1)));
        //then
    }

    @Test
    @Transactional
    public void should_return_application_problem_by_id() throws Exception {
        // given
        ApplicationProblem applicationProblem = new ApplicationProblem(
                2L,"keywords", "description","v1.02", 1L);
        applicationProblemsService.save(applicationProblem);
        // when
        mockMvc.perform(get("/application-problems/2"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.applicationProblemId", Matchers.is(2)))
                .andExpect(jsonPath("$.keywords", Matchers.is("keywords")))
                .andExpect(jsonPath("$.description", Matchers.is("description")))
                .andExpect(jsonPath("$.versionCode", Matchers.is("v1.02")))
                .andExpect(jsonPath("$.userId", Matchers.is(1)));
        //then
    }

    @Test
    @Transactional
    public void should_add_application_problem() throws Exception {
        // given
        ApplicationProblem applicationProblem = new ApplicationProblem(
                null,"ERROR", "new error","v1.01", 1L);
        applicationProblemsService.save(applicationProblem);
        // when
        mockMvc.perform(post("/application-problems")
                .content(objectMapper.writeValueAsString(applicationProblem))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.keywords", Matchers.is("ERROR")))
                .andExpect(jsonPath("$.description", Matchers.is("new error")))
                .andExpect(jsonPath("$.versionCode", Matchers.is("v1.01")))
                .andExpect(jsonPath("$.userId", Matchers.is(1)));
        //then
    }

    @Test
    @Transactional
    public void should_delete_application_problem_by_id() throws Exception {
        // given
        ApplicationProblem applicationProblem = new ApplicationProblem(
                2L,"ERROR", "new error","v1.01", 1L);
        applicationProblemsService.save(applicationProblem);
        // when
        mockMvc.perform(delete("/application-problems/2"))
                .andDo(print())
                .andExpect(status().is(200));
        //then
    }

}