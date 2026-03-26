package com.coderscampus.cp.web;

import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StudentRepository studentRepository;
    private final List<String> createdUids = new ArrayList<>();

    @AfterEach
    void cleanUp() {
        createdUids.stream()
                .map(studentRepository::findByUid)
                .filter(student -> student != null)
                .forEach(studentRepository::delete);
        createdUids.clear();
    }


    @Test
    public void testGetCreate() throws Exception {
        StudentDTO student = new StudentDTO();
        student.setName("bobby");
        String uid = UUID.randomUUID().toString();
        createdUids.add(uid);


        mockMvc.perform(post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                        .param("uid", uid))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testReadAllShowsStudentsWithoutCheckins() throws Exception {
        StudentDTO student = new StudentDTO();
        student.setName("Sahar Ayazian");
        String uid = UUID.randomUUID().toString();
        createdUids.add(uid);

        mockMvc.perform(post("/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                        .param("uid", uid))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/student/readall"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sahar Ayazian")));
    }

}
