package com.coderscampus.cp.web;
import com.coderscampus.cp.dto.AuthObjectDto;
import com.coderscampus.cp.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


//    @Test
//    public void testGetCreate() throws Exception {
//        StudentDTO student = new StudentDTO();
//        student.setName("bobby");
//        String uid = UUID.randomUUID().toString();
//
//
//        mockMvc.perform(post("/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(student))
//                        .param("uid", uid))
//                .andExpect(status().isOk());
//    }


    @Test
    public void should_authenticate_user () throws Exception {
        AuthObjectDto authObjectDto = new AuthObjectDto();
        authObjectDto.setDisplayName("test_user");
        authObjectDto.setUid("test_uid");

        MockHttpSession httpSession = new MockHttpSession();
        mockMvc.perform(post("/send-oauth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonString(authObjectDto))
                        .session(httpSession)
                        .sessionAttr("uid", "test_uid")
                                .sessionAttr("displayName", "test_user")
                )


                .andExpect(status().isOk());


        assertEquals("test_uid", httpSession.getAttribute("uid"));
        assertEquals("test_user", httpSession.getAttribute("displayName"));


    }

    @Test
    public void should_not_authenticate_user () throws Exception {
            mockMvc.perform(post("/send-oauth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(convertObjectToJsonString(null)))
                    .andExpect(status().isBadRequest());
    }


    private static String convertObjectToJsonString(Object object) {
         ObjectMapper objectMapper = new ObjectMapper();
        try {
           return objectMapper.writeValueAsString(object);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }




}
