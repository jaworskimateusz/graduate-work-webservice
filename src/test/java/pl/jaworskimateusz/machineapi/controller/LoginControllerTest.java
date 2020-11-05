package pl.jaworskimateusz.machineapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_login_and_return_content() throws Exception {
        MvcResult login = mockMvc.perform(post("/login")
                .content("{\"username\": \"mateusz123\", \"password\": \"test123\"}"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        String token = login.getResponse().getHeader("Authorization");

        mockMvc.perform(get("/secured")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string("secured"));

        mockMvc.perform(get("/secured"))
                .andDo(print())
                .andExpect(status().is(401));
    }

    @Test
    public void should_not_login_without_token() throws Exception {
        mockMvc.perform(post("/login")
                .content("{\"username\": \"mateusz123\", \"password\": \"test123\"}"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        mockMvc.perform(get("/secured"))
                .andDo(print())
                .andExpect(status().is(401));
    }

}