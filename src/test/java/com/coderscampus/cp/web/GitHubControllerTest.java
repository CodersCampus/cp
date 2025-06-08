package com.coderscampus.cp.web;


import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.GitHubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class GitHubControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GitHubService gitHubService;


    @Test
    public void testRedirectOnGetWithWrongUID() throws Exception {

        Long gitHubId = 1L;
        String sessionUid = "wrong-uid";

        GitHub mockGitHub = new GitHub();
        mockGitHub.setId(gitHubId);
        Student student = new Student();
        student.setUid("correct-uid");
        mockGitHub.setStudent(student);

        Mockito.when(gitHubService.findById(gitHubId)).thenReturn(mockGitHub);

        mockMvc.perform(get("/github/update/{id}", gitHubId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/github"));
    }

    @Test
    public void testRedirectOnGetWithCorrectUID() throws Exception {

        Long gitHubId = 1L;
        String sessionUid = "wrong-uid";

        GitHub mockGitHub = new GitHub();
        mockGitHub.setId(gitHubId);
        Student student = new Student();
        student.setUid(sessionUid);
        mockGitHub.setStudent(student);

        Mockito.when(gitHubService.findById(gitHubId)).thenReturn(mockGitHub);

        mockMvc.perform(get("/github/update/{id}", gitHubId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(view().name("github/update"));
    }

}
