package com.coderscampus.cp.web.api;

import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.service.FinalprojectService;
import com.coderscampus.cp.service.GitHubService;
import com.coderscampus.cp.service.LinkedInService;
import com.coderscampus.cp.service.NetworkingpersonService;
import com.coderscampus.cp.service.NetworkingresourceService;
import com.coderscampus.cp.service.ResumeService;
import com.coderscampus.cp.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BackendRestController.class)
@Import(ApiExceptionHandler.class)
class BackendRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ApiSessionService apiSessionService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private GitHubService gitHubService;

    @MockBean
    private LinkedInService linkedInService;

    @MockBean
    private ResumeService resumeService;

    @MockBean
    private FinalprojectService finalprojectService;

    @MockBean
    private NetworkingpersonService networkingpersonService;

    @MockBean
    private NetworkingresourceService networkingresourceService;

    @Test
    void shouldReturnUnauthorizedWhenSessionHasNoUid() throws Exception {
        doThrow(new UnauthorizedException()).when(apiSessionService).requireUid(any());

        mockMvc.perform(get("/api/github-profiles"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Unauthorized"))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    @Test
    void shouldReturnOnlyCurrentUsersGitHubProfiles() throws Exception {
        String uid = "student-uid";
        GitHub gitHub = gitHub(1L, 7L, uid);
        gitHub.setUrl("https://github.com/example");
        when(apiSessionService.requireUid(any())).thenReturn(uid);
        when(gitHubService.findByUid(uid)).thenReturn(List.of(gitHub));

        mockMvc.perform(get("/api/github-profiles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].studentId").value(7))
                .andExpect(jsonPath("$[0].url").value("https://github.com/example"));
    }

    @Test
    void shouldReturnForbiddenWhenGitHubProfileBelongsToAnotherUser() throws Exception {
        String uid = "student-uid";
        when(apiSessionService.requireUid(any())).thenReturn(uid);
        when(gitHubService.findByIdAndUid(1L, uid)).thenReturn(Optional.empty());
        when(gitHubService.findOptionalById(1L)).thenReturn(Optional.of(gitHub(1L, 8L, "other-uid")));

        mockMvc.perform(get("/api/github-profiles/1"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Forbidden"));
    }

    @Test
    void shouldReturnNotFoundWhenGitHubProfileDoesNotExist() throws Exception {
        String uid = "student-uid";
        when(apiSessionService.requireUid(any())).thenReturn(uid);
        when(gitHubService.findByIdAndUid(1L, uid)).thenReturn(Optional.empty());
        when(gitHubService.findOptionalById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/github-profiles/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Not found"));
    }

    @Test
    void shouldReturnConflictWhenCreatingSecondGitHubProfile() throws Exception {
        String uid = "student-uid";
        when(apiSessionService.requireUid(any())).thenReturn(uid);
        when(gitHubService.checkIfExists(uid)).thenReturn(true);

        mockMvc.perform(post("/api/github-profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new GitHubPayload("https://github.com/example"))))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Current student already has a GitHub profile"));
    }

    @Test
    void shouldCreateGitHubProfile() throws Exception {
        String uid = "student-uid";
        GitHub saved = gitHub(1L, 7L, uid);
        saved.setUrl("https://github.com/example");
        when(apiSessionService.requireUid(any())).thenReturn(uid);
        when(gitHubService.checkIfExists(uid)).thenReturn(false);
        when(gitHubService.isValidURL("https://github.com/example")).thenReturn(true);
        when(gitHubService.saveByUid(any(GitHub.class), eq(uid))).thenReturn(saved);

        mockMvc.perform(post("/api/github-profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new GitHubPayload("https://github.com/example"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.studentId").value(7))
                .andExpect(jsonPath("$.url").value("https://github.com/example"));
    }

    @Test
    void shouldDeleteOwnedGitHubProfile() throws Exception {
        String uid = "student-uid";
        GitHub gitHub = gitHub(1L, 7L, uid);
        when(apiSessionService.requireUid(any())).thenReturn(uid);
        when(gitHubService.findByIdAndUid(1L, uid)).thenReturn(Optional.of(gitHub));
        when(gitHubService.findOptionalById(1L)).thenReturn(Optional.of(gitHub));

        mockMvc.perform(delete("/api/github-profiles/1"))
                .andExpect(status().isNoContent());

        verify(gitHubService).delete(gitHub);
    }

    private GitHub gitHub(Long id, Long studentId, String uid) {
        Student student = new Student();
        ReflectionTestUtils.setField(student, "id", studentId);
        student.setUid(uid);
        GitHub gitHub = new GitHub();
        gitHub.setId(id);
        gitHub.setStudent(student);
        return gitHub;
    }

    private record GitHubPayload(String url) {
    }
}
