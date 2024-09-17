package com.coderscampus.cp.web;

import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.repository.SpringProjectRepository;
import com.coderscampus.cp.service.CheckinService;
import com.coderscampus.cp.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@WebMvcTest(SpringProjectController.class)
public class RestControllerTest {

    @MockBean
    private SpringProjectRepository springProjectRepository;
    @MockBean
    private StudentService studentService;
    @MockBean
    private CheckinService checkinService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    MockHttpSession mockHttpSession;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    void setup(){
        mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
    }

    @Test
    void should_return_get_auth_response() throws Exception{
        AuthObjectDTO authObjectDTO = new AuthObjectDTO();
        authObjectDTO.setUid(UUID.randomUUID().toString());
        authObjectDTO.setDisplayName("display_name");

        MvcResult mvcResult = mockMvc.perform(post("/send-oauth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authObjectDTO))
                        .session(mockHttpSession))
                .andExpect(status().isOk())
                .andReturn();

        var response = mvcResult.getResponse().getContentAsString();

        assertEquals(response, "redirect:/");
    }

}
