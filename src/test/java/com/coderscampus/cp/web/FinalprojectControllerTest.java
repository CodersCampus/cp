package com.coderscampus.cp.web;


import com.coderscampus.cp.domain.Finalproject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.FinalprojectService;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class FinalprojectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FinalprojectService finalprojectService;


    @Test
    public void testRedirectOnGetWithWrongUID() throws Exception {

        Long finalprojectId = 1L;
        String sessionUid = "wrong-uid";

        Finalproject mockFinalproject = new Finalproject();
        mockFinalproject.setId(finalprojectId);
        Student student = new Student();
        student.setUid("correct-uid");
        mockFinalproject.setStudent(student);

        Mockito.when(finalprojectService.findById(finalprojectId)).thenReturn(mockFinalproject);

        mockMvc.perform(get("/finalproject/update/{id}", finalprojectId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/finalproject"));
    }

    @Test
    public void testRedirectOnGetWithCorrectUID() throws Exception {

        Long finalprojectId = 1L;
        String sessionUid = "wrong-uid";

        Finalproject mockFinalproject = new Finalproject();
        mockFinalproject.setId(finalprojectId);
        Student student = new Student();
        student.setUid(sessionUid);
        mockFinalproject.setStudent(student);

        Mockito.when(finalprojectService.findById(finalprojectId)).thenReturn(mockFinalproject);

        mockMvc.perform(get("/finalproject/update/{id}", finalprojectId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(view().name("finalproject/update"));
    }

    @Test
    public void testRedirectOnGetWhereObjectHasNoStudent() throws Exception {

        Long finalprojectId = 1L;
        String sessionUid = UUID.randomUUID().toString();

        Finalproject mockFinalproject = new Finalproject();
        mockFinalproject.setId(finalprojectId);

        Mockito.when(finalprojectService.findById(finalprojectId)).thenReturn(mockFinalproject);

        mockMvc.perform(get("/finalproject/update/{id}", finalprojectId)
                    .sessionAttr("uid", sessionUid))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/finalproject"));
    }

}
