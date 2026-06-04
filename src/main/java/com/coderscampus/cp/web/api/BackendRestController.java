package com.coderscampus.cp.web.api;

import com.coderscampus.cp.domain.Finalproject;
import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Networkingperson;
import com.coderscampus.cp.domain.Networkingresource;
import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.ApiDtos.FinalProjectRequest;
import com.coderscampus.cp.dto.ApiDtos.FinalProjectResponse;
import com.coderscampus.cp.dto.ApiDtos.GitHubProfileRequest;
import com.coderscampus.cp.dto.ApiDtos.GitHubProfileResponse;
import com.coderscampus.cp.dto.ApiDtos.LinkedInProfileRequest;
import com.coderscampus.cp.dto.ApiDtos.LinkedInProfileResponse;
import com.coderscampus.cp.dto.ApiDtos.NetworkingPersonRequest;
import com.coderscampus.cp.dto.ApiDtos.NetworkingPersonResponse;
import com.coderscampus.cp.dto.ApiDtos.NetworkingResourceRequest;
import com.coderscampus.cp.dto.ApiDtos.NetworkingResourceResponse;
import com.coderscampus.cp.dto.ApiDtos.ResumeRequest;
import com.coderscampus.cp.dto.ApiDtos.ResumeResponse;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.service.FinalprojectService;
import com.coderscampus.cp.service.GitHubService;
import com.coderscampus.cp.service.LinkedInService;
import com.coderscampus.cp.service.NetworkingpersonService;
import com.coderscampus.cp.service.NetworkingresourceService;
import com.coderscampus.cp.service.ResumeService;
import com.coderscampus.cp.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BackendRestController {

    private final ApiSessionService apiSessionService;
    private final StudentService studentService;
    private final GitHubService gitHubService;
    private final LinkedInService linkedInService;
    private final ResumeService resumeService;
    private final FinalprojectService finalprojectService;
    private final NetworkingpersonService networkingpersonService;
    private final NetworkingresourceService networkingresourceService;

    public BackendRestController(ApiSessionService apiSessionService, StudentService studentService,
            GitHubService gitHubService, LinkedInService linkedInService, ResumeService resumeService,
            FinalprojectService finalprojectService, NetworkingpersonService networkingpersonService,
            NetworkingresourceService networkingresourceService) {
        this.apiSessionService = apiSessionService;
        this.studentService = studentService;
        this.gitHubService = gitHubService;
        this.linkedInService = linkedInService;
        this.resumeService = resumeService;
        this.finalprojectService = finalprojectService;
        this.networkingpersonService = networkingpersonService;
        this.networkingresourceService = networkingresourceService;
    }

    @GetMapping("/students/me")
    public StudentDTO getCurrentStudent(HttpSession session) {
        return new StudentDTO(apiSessionService.requireStudent(session));
    }

    @PatchMapping("/students/me")
    public StudentDTO updateCurrentStudent(@RequestBody StudentDTO request, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        Student currentStudent = apiSessionService.requireStudent(session);
        StudentDTO studentDTO = new StudentDTO(currentStudent);
        if (StringUtils.hasText(request.getName())) {
            studentDTO.setName(request.getName());
        }
        studentDTO.setAssignmentNum(request.getAssignmentNum());
        studentDTO.setIde(request.getIde());
        studentDTO.setWillingToMentor(request.getWillingToMentor());
        studentDTO.setMentee(request.getMentee());
        return studentService.saveByUid(studentDTO, uid);
    }

    @GetMapping("/github-profiles")
    public List<GitHubProfileResponse> getGitHubProfiles(HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return gitHubService.findByUid(uid).stream().map(GitHubProfileResponse::from).toList();
    }

    @GetMapping("/github-profiles/{id}")
    public GitHubProfileResponse getGitHubProfile(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return GitHubProfileResponse.from(requireOwned(gitHubService.findByIdAndUid(id, uid), gitHubService.findOptionalById(id)));
    }

    @PostMapping("/github-profiles")
    public ResponseEntity<GitHubProfileResponse> createGitHubProfile(@RequestBody GitHubProfileRequest request,
            HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        if (gitHubService.checkIfExists(uid)) {
            throw new ConflictException("Current student already has a GitHub profile");
        }
        validateUrl("url", request.url(), gitHubService::isValidURL);
        GitHub gitHub = gitHubService.saveByUid(request.toDomain(null), uid);
        return ResponseEntity.status(HttpStatus.CREATED).body(GitHubProfileResponse.from(gitHub));
    }

    @PutMapping("/github-profiles/{id}")
    public GitHubProfileResponse updateGitHubProfile(@PathVariable Long id, @RequestBody GitHubProfileRequest request,
            HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        requireOwned(gitHubService.findByIdAndUid(id, uid), gitHubService.findOptionalById(id));
        validateUrl("url", request.url(), gitHubService::isValidURL);
        return GitHubProfileResponse.from(gitHubService.saveByUid(request.toDomain(id), uid));
    }

    @DeleteMapping("/github-profiles/{id}")
    public ResponseEntity<Void> deleteGitHubProfile(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        gitHubService.delete(requireOwned(gitHubService.findByIdAndUid(id, uid), gitHubService.findOptionalById(id)));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/linkedin-profiles")
    public List<LinkedInProfileResponse> getLinkedInProfiles(HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return linkedInService.findByUid(uid).stream().map(LinkedInProfileResponse::from).toList();
    }

    @GetMapping("/linkedin-profiles/{id}")
    public LinkedInProfileResponse getLinkedInProfile(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return LinkedInProfileResponse.from(requireOwned(linkedInService.findByIdAndUid(id, uid), linkedInService.findOptionalById(id)));
    }

    @PostMapping("/linkedin-profiles")
    public ResponseEntity<LinkedInProfileResponse> createLinkedInProfile(@RequestBody LinkedInProfileRequest request,
            HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        if (linkedInService.checkIfExists(uid)) {
            throw new ConflictException("Current student already has a LinkedIn profile");
        }
        validateUrl("url", request.url(), linkedInService::isValidURL);
        LinkedIn linkedIn = linkedInService.saveByUid(request.toDomain(null), uid);
        return ResponseEntity.status(HttpStatus.CREATED).body(LinkedInProfileResponse.from(linkedIn));
    }

    @PutMapping("/linkedin-profiles/{id}")
    public LinkedInProfileResponse updateLinkedInProfile(@PathVariable Long id,
            @RequestBody LinkedInProfileRequest request, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        requireOwned(linkedInService.findByIdAndUid(id, uid), linkedInService.findOptionalById(id));
        validateUrl("url", request.url(), linkedInService::isValidURL);
        return LinkedInProfileResponse.from(linkedInService.saveByUid(request.toDomain(id), uid));
    }

    @DeleteMapping("/linkedin-profiles/{id}")
    public ResponseEntity<Void> deleteLinkedInProfile(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        linkedInService.delete(requireOwned(linkedInService.findByIdAndUid(id, uid), linkedInService.findOptionalById(id)));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resumes")
    public List<ResumeResponse> getResumes(HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return resumeService.findByUid(uid).stream().map(ResumeResponse::from).toList();
    }

    @GetMapping("/resumes/{id}")
    public ResumeResponse getResume(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return ResumeResponse.from(requireOwned(resumeService.findByIdAndUid(id, uid), resumeService.findOptionalById(id)));
    }

    @PostMapping("/resumes")
    public ResponseEntity<ResumeResponse> createResume(@RequestBody ResumeRequest request, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        if (resumeService.checkIfExists(uid)) {
            throw new ConflictException("Current student already has a resume");
        }
        Resume resume = resumeService.saveByUid(request.toDomain(null), uid);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResumeResponse.from(resume));
    }

    @PutMapping("/resumes/{id}")
    public ResumeResponse updateResume(@PathVariable Long id, @RequestBody ResumeRequest request, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        requireOwned(resumeService.findByIdAndUid(id, uid), resumeService.findOptionalById(id));
        return ResumeResponse.from(resumeService.saveByUid(request.toDomain(id), uid));
    }

    @DeleteMapping("/resumes/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        resumeService.delete(requireOwned(resumeService.findByIdAndUid(id, uid), resumeService.findOptionalById(id)));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/final-projects")
    public List<FinalProjectResponse> getFinalProjects(HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return finalprojectService.findByUid(uid).stream().map(FinalProjectResponse::from).toList();
    }

    @GetMapping("/final-projects/{id}")
    public FinalProjectResponse getFinalProject(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return FinalProjectResponse.from(requireOwned(finalprojectService.findByIdAndUid(id, uid), finalprojectService.findOptionalById(id)));
    }

    @PostMapping("/final-projects")
    public ResponseEntity<FinalProjectResponse> createFinalProject(@RequestBody FinalProjectRequest request,
            HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        validateUrl("proposal", request.proposal(), finalprojectService::isValidURL);
        Finalproject finalproject = finalprojectService.saveByUid(request.toDomain(null), uid);
        return ResponseEntity.status(HttpStatus.CREATED).body(FinalProjectResponse.from(finalproject));
    }

    @PutMapping("/final-projects/{id}")
    public FinalProjectResponse updateFinalProject(@PathVariable Long id, @RequestBody FinalProjectRequest request,
            HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        requireOwned(finalprojectService.findByIdAndUid(id, uid), finalprojectService.findOptionalById(id));
        validateUrl("proposal", request.proposal(), finalprojectService::isValidURL);
        return FinalProjectResponse.from(finalprojectService.saveByUid(request.toDomain(id), uid));
    }

    @DeleteMapping("/final-projects/{id}")
    public ResponseEntity<Void> deleteFinalProject(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        finalprojectService.delete(requireOwned(finalprojectService.findByIdAndUid(id, uid), finalprojectService.findOptionalById(id)));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/networking-people")
    public List<NetworkingPersonResponse> getNetworkingPeople(HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return networkingpersonService.findByUid(uid).stream().map(NetworkingPersonResponse::from).toList();
    }

    @GetMapping("/networking-people/{id}")
    public NetworkingPersonResponse getNetworkingPerson(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return NetworkingPersonResponse.from(requireOwned(networkingpersonService.findByIdAndUid(id, uid), networkingpersonService.findOptionalById(id)));
    }

    @PostMapping("/networking-people")
    public ResponseEntity<NetworkingPersonResponse> createNetworkingPerson(@RequestBody NetworkingPersonRequest request,
            HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        Networkingperson person = networkingpersonService.saveByUid(request.toDomain(null), uid);
        return ResponseEntity.status(HttpStatus.CREATED).body(NetworkingPersonResponse.from(person));
    }

    @PutMapping("/networking-people/{id}")
    public NetworkingPersonResponse updateNetworkingPerson(@PathVariable Long id,
            @RequestBody NetworkingPersonRequest request, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        requireOwned(networkingpersonService.findByIdAndUid(id, uid), networkingpersonService.findOptionalById(id));
        return NetworkingPersonResponse.from(networkingpersonService.saveByUid(request.toDomain(id), uid));
    }

    @DeleteMapping("/networking-people/{id}")
    public ResponseEntity<Void> deleteNetworkingPerson(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        networkingpersonService.delete(requireOwned(networkingpersonService.findByIdAndUid(id, uid), networkingpersonService.findOptionalById(id)));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/networking-resources")
    public List<NetworkingResourceResponse> getNetworkingResources(HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return networkingresourceService.findByUid(uid).stream().map(NetworkingResourceResponse::from).toList();
    }

    @GetMapping("/networking-resources/{id}")
    public NetworkingResourceResponse getNetworkingResource(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        return NetworkingResourceResponse.from(requireOwned(networkingresourceService.findByIdAndUid(id, uid), networkingresourceService.findOptionalById(id)));
    }

    @PostMapping("/networking-resources")
    public ResponseEntity<NetworkingResourceResponse> createNetworkingResource(
            @RequestBody NetworkingResourceRequest request, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        Networkingresource resource = networkingresourceService.saveByUid(request.toDomain(null), uid);
        return ResponseEntity.status(HttpStatus.CREATED).body(NetworkingResourceResponse.from(resource));
    }

    @PutMapping("/networking-resources/{id}")
    public NetworkingResourceResponse updateNetworkingResource(@PathVariable Long id,
            @RequestBody NetworkingResourceRequest request, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        requireOwned(networkingresourceService.findByIdAndUid(id, uid), networkingresourceService.findOptionalById(id));
        return NetworkingResourceResponse.from(networkingresourceService.saveByUid(request.toDomain(id), uid));
    }

    @DeleteMapping("/networking-resources/{id}")
    public ResponseEntity<Void> deleteNetworkingResource(@PathVariable Long id, HttpSession session) {
        String uid = apiSessionService.requireUid(session);
        networkingresourceService.delete(requireOwned(networkingresourceService.findByIdAndUid(id, uid), networkingresourceService.findOptionalById(id)));
        return ResponseEntity.noContent().build();
    }

    private <T> T requireOwned(Optional<T> ownedResource, Optional<T> existingResource) {
        if (ownedResource.isPresent()) {
            return ownedResource.get();
        }
        if (existingResource.isPresent()) {
            throw new ForbiddenException();
        }
        throw new ResourceNotFoundException();
    }

    private void validateUrl(String field, String value, UrlValidator validator) {
        if (StringUtils.hasText(value) && !validator.isValid(value)) {
            throw new ValidationException(Map.of(field, "Invalid URL"));
        }
    }

    private interface UrlValidator {
        boolean isValid(String value);
    }
}
