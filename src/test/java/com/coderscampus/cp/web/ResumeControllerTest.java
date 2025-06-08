package com.coderscampus.cp.web;


import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.ResumeService;
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

public class ResumeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ResumeService resumeService;


    @Test
    public void testRedirectOnGetWithWrongUID() throws Exception {

        Long resumeId = 1L;
        String sessionUid = "wrong-uid";

        Resume mockResume = new Resume();
        mockResume.setId(resumeId);
        Student student = new Student();
        student.setUid("correct-uid");
        mockResume.setStudent(student);

        Mockito.when(resumeService.findById(resumeId)).thenReturn(mockResume);

        mockMvc.perform(get("/resume/update/{id}", resumeId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/resume"));
    }

    @Test
    public void testRedirectOnGetWithCorrectUID() throws Exception {

        Long resumeId = 1L;
        String sessionUid = "wrong-uid";

        Resume mockResume = new Resume();
        mockResume.setId(resumeId);
        Student student = new Student();
        student.setUid(sessionUid);
        mockResume.setStudent(student);

        Mockito.when(resumeService.findById(resumeId)).thenReturn(mockResume);

        mockMvc.perform(get("/resume/update/{id}", resumeId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(view().name("resume/update"));
    }

}
