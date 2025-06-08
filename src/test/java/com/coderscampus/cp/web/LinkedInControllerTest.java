package com.coderscampus.cp.web;


import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.LinkedInService;
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

public class LinkedInControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private LinkedInService linkedInService;


    @Test
    public void testRedirectOnGetWithWrongUID() throws Exception {

        Long linkedInId = 1L;
        String sessionUid = "wrong-uid";

        LinkedIn mockLinkedIn = new LinkedIn();
        mockLinkedIn.setId(linkedInId);
        Student student = new Student();
        student.setUid("correct-uid");
        mockLinkedIn.setStudent(student);

        Mockito.when(linkedInService.findById(linkedInId)).thenReturn(mockLinkedIn);

        mockMvc.perform(get("/linkedin/update/{id}", linkedInId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/linkedin"));
    }

    @Test
    public void testRedirectOnGetWithCorrectUID() throws Exception {

        Long linkedInId = 1L;
        String sessionUid = "wrong-uid";

        LinkedIn mockLinkedIn = new LinkedIn();
        mockLinkedIn.setId(linkedInId);
        Student student = new Student();
        student.setUid(sessionUid);
        mockLinkedIn.setStudent(student);

        Mockito.when(linkedInService.findById(linkedInId)).thenReturn(mockLinkedIn);

        mockMvc.perform(get("/linkedin/update/{id}", linkedInId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(view().name("linkedin/update"));
    }

}
