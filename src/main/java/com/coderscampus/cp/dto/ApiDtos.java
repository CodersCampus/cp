package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.Finalproject;
import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Networkingperson;
import com.coderscampus.cp.domain.Networkingresource;
import com.coderscampus.cp.domain.Resume;

import java.util.Map;

public final class ApiDtos {

    private ApiDtos() {
    }

    public record ApiErrorResponse(String message, Map<String, String> errors) {
    }

    public record GitHubProfileRequest(Boolean handle, Boolean enhancedReadMe, Boolean renamedAssignments,
            Boolean pinnedRepos, String externalLinks, Boolean image, Boolean headline, Boolean contactDetails,
            String url) {
        public GitHub toDomain(Long id) {
            GitHub gitHub = new GitHub();
            gitHub.setId(id);
            gitHub.setHandle(handle);
            gitHub.setEnhancedReadMe(enhancedReadMe);
            gitHub.setRenamedAssignments(renamedAssignments);
            gitHub.setPinnedRepos(pinnedRepos);
            gitHub.setExternalLinks(externalLinks);
            gitHub.setImage(image);
            gitHub.setHeadline(headline);
            gitHub.setContactDetails(contactDetails);
            gitHub.setUrl(url);
            return gitHub;
        }
    }

    public record GitHubProfileResponse(Long id, Long studentId, Boolean handle, Boolean enhancedReadMe,
            Boolean renamedAssignments, Boolean pinnedRepos, String externalLinks, Boolean image, Boolean headline,
            Boolean contactDetails, String url) {
        public static GitHubProfileResponse from(GitHub gitHub) {
            return new GitHubProfileResponse(gitHub.getId(), resolveStudentId(gitHub.getStudent()), gitHub.getHandle(),
                    gitHub.getEnhancedReadMe(), gitHub.getRenamedAssignments(), gitHub.getPinnedRepos(),
                    gitHub.getExternalLinks(), gitHub.getImage(), gitHub.getHeadline(), gitHub.getContactDetails(),
                    gitHub.getUrl());
        }
    }

    public record LinkedInProfileRequest(String firstName, String lastName, String url, Boolean banner, Boolean about,
            Boolean featuredPosts, Boolean activity, Boolean skills, Boolean email, Boolean biography,
            Boolean education, Boolean experience, Boolean location, Boolean image, Boolean title) {
        public LinkedIn toDomain(Long id) {
            LinkedIn linkedIn = new LinkedIn();
            linkedIn.setId(id);
            linkedIn.setFirstName(firstName);
            linkedIn.setLastName(lastName);
            linkedIn.setUrl(url);
            linkedIn.setBanner(banner);
            linkedIn.setAbout(about);
            linkedIn.setFeaturedPosts(featuredPosts);
            linkedIn.setActivity(activity);
            linkedIn.setSkills(skills);
            linkedIn.setEmail(email);
            linkedIn.setBiography(biography);
            linkedIn.setEducation(education);
            linkedIn.setExperience(experience);
            linkedIn.setLocation(location);
            linkedIn.setImage(image);
            linkedIn.setTitle(title);
            return linkedIn;
        }
    }

    public record LinkedInProfileResponse(Long id, Long studentId, String firstName, String lastName, String url,
            Boolean banner, Boolean about, Boolean featuredPosts, Boolean activity, Boolean skills, Boolean email,
            Boolean biography, Boolean education, Boolean experience, Boolean location, Boolean image,
            Boolean title) {
        public static LinkedInProfileResponse from(LinkedIn linkedIn) {
            return new LinkedInProfileResponse(linkedIn.getId(), resolveStudentId(linkedIn.getStudent()),
                    linkedIn.getFirstName(), linkedIn.getLastName(), linkedIn.getUrl(), linkedIn.getBanner(),
                    linkedIn.getAbout(), linkedIn.getFeaturedPosts(), linkedIn.getActivity(), linkedIn.getSkills(),
                    linkedIn.getEmail(), linkedIn.getBiography(), linkedIn.getEducation(), linkedIn.getExperience(),
                    linkedIn.getLocation(), linkedIn.getImage(), linkedIn.getTitle());
        }
    }

    public record ResumeRequest(String phoneNumber, String email, String city, String state, String linkedIn,
            String gitHub, String summary, String skills, String workExperience, String education, String projects) {
        public Resume toDomain(Long id) {
            Resume resume = new Resume();
            resume.setId(id);
            resume.setPhoneNumber(phoneNumber);
            resume.setEmail(email);
            resume.setCity(city);
            resume.setState(state);
            resume.setLinkedIn(linkedIn);
            resume.setGitHub(gitHub);
            resume.setSummary(summary);
            resume.setSkills(skills);
            resume.setWorkExperience(workExperience);
            resume.setEducation(education);
            resume.setProjects(projects);
            return resume;
        }
    }

    public record ResumeResponse(Long id, Long studentId, String phoneNumber, String email, String city, String state,
            String linkedIn, String gitHub, String summary, String skills, String workExperience, String education,
            String projects) {
        public static ResumeResponse from(Resume resume) {
            return new ResumeResponse(resume.getId(), resolveStudentId(resume.getStudent()), resume.getPhoneNumber(),
                    resume.getEmail(), resume.getCity(), resume.getState(), resume.getLinkedIn(), resume.getGitHub(),
                    resume.getSummary(), resume.getSkills(), resume.getWorkExperience(), resume.getEducation(),
                    resume.getProjects());
        }
    }

    public record FinalProjectRequest(String projectName, String proposal, String description, String crud,
            String tables, String views) {
        public Finalproject toDomain(Long id) {
            Finalproject finalproject = new Finalproject();
            finalproject.setId(id);
            finalproject.setProjectName(projectName);
            finalproject.setProposal(proposal);
            finalproject.setDescription(description);
            finalproject.setCrud(crud);
            finalproject.setTables(tables);
            finalproject.setViews(views);
            return finalproject;
        }
    }

    public record FinalProjectResponse(Long id, Long studentId, String projectName, String proposal,
            String description, String crud, String tables, String views) {
        public static FinalProjectResponse from(Finalproject finalproject) {
            return new FinalProjectResponse(finalproject.getId(), resolveStudentId(finalproject.getStudent()),
                    finalproject.getProjectName(), finalproject.getProposal(), finalproject.getDescription(),
                    finalproject.getCrud(), finalproject.getTables(), finalproject.getViews());
        }
    }

    public record NetworkingPersonRequest(String name, String linkedinUrl, String techStack, String firstContactDate,
            String lastContactDate, String otherNotesAboutPerson) {
        public Networkingperson toDomain(Long id) {
            Networkingperson person = new Networkingperson();
            person.setId(id);
            person.setName(name);
            person.setLinkedinUrl(linkedinUrl);
            person.setTechStack(techStack);
            person.setFirstContactDate(firstContactDate);
            person.setLastContactDate(lastContactDate);
            person.setOtherNotesAboutPerson(otherNotesAboutPerson);
            return person;
        }
    }

    public record NetworkingPersonResponse(Long id, Long studentId, String name, String linkedinUrl, String techStack,
            String firstContactDate, String lastContactDate, String otherNotesAboutPerson) {
        public static NetworkingPersonResponse from(Networkingperson person) {
            return new NetworkingPersonResponse(person.getId(), resolveStudentId(person.getStudent()), person.getName(),
                    person.getLinkedinUrl(), person.getTechStack(), person.getFirstContactDate(),
                    person.getLastContactDate(), person.getOtherNotesAboutPerson());
        }
    }

    public record NetworkingResourceRequest(String resourceName, String type, String cost, String geographicScope,
            String notes) {
        public Networkingresource toDomain(Long id) {
            Networkingresource resource = new Networkingresource();
            resource.setId(id);
            resource.setResourceName(resourceName);
            resource.setType(type);
            resource.setCost(cost);
            resource.setGeographicScope(geographicScope);
            resource.setNotes(notes);
            return resource;
        }
    }

    public record NetworkingResourceResponse(Long id, Long studentId, String resourceName, String type, String cost,
            String geographicScope, String notes) {
        public static NetworkingResourceResponse from(Networkingresource resource) {
            return new NetworkingResourceResponse(resource.getId(), resolveStudentId(resource.getStudent()),
                    resource.getResourceName(), resource.getType(), resource.getCost(), resource.getGeographicScope(),
                    resource.getNotes());
        }
    }

    private static Long resolveStudentId(com.coderscampus.cp.domain.Student student) {
        return student == null ? null : student.getId();
    }
}
