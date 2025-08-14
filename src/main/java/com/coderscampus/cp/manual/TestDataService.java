package com.coderscampus.cp.manual;

import com.coderscampus.cp.domain.*;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.*;
import com.coderscampus.cp.service.FinalprojectService;
import com.coderscampus.cp.service.GitHubService;
import com.coderscampus.cp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TestDataService {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private StudentService studentService;

    @Autowired
    private GitHubService gitHubService;
    @Autowired
    private GitHubRepository gitHubRepo;

    @Autowired
    private FinalprojectService finalprojectService;
    @Autowired
    private FinalprojectRepository finalprojectRepo;
    @Autowired
    private LinkedInRepository linkedInRepo;
    @Autowired
    private NetworkingpersonRepository networkingpersonRepo;
    @Autowired
    private NetworkingresourceRepository networkingresourceRepo;
    @Autowired
    private ResumeRepository resumeRepo;

    Student student1;
    Student student2;

    StudentDTO studentDTO1;
    StudentDTO studentDTO2;

    String student1Uid;
    String student2Uid;

    List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

    List<StudentDTO> student1StudentDTOList;

    List<GitHub> student1GitHubList;

    List<Finalproject> student1FinalprojectList;

    void prepData() throws MalformedURLException {

        studentDTO1 = new StudentDTO();
        studentDTO2 = new StudentDTO();

        student1Uid = "student1";
        student2Uid = "student2";

        student1 = new Student(student1Uid, "name1", 1, "IntelliJ", false, "mentor1", null);
        student2 = new Student(student2Uid, "name2", 2, "IntelliJ", false, "mentor2", null);

        student1 = studentRepo.save(student1);
        student2 = studentRepo.save(student2);

        student1GitHubList = new ArrayList<>();

        List<StudentDTO> student1StudentDTOList = new ArrayList<StudentDTO>();

        student1 = studentRepo.save(student1);
        student2 = studentRepo.save(student2);


        for (int j = 0; j < 2; j++) {
            String student1Uid1 = student1Uid;
            if (j == 1) {
                student1Uid = student2Uid;
                student1= student2;
            }

            for (int i = 0; i < 4; i++) {
                GitHub gitHub = new GitHub();
                gitHub.setUrl("Blocker" + i);
                gitHub.setHandle(true);
                gitHub.setEnhancedReadMe(true);
                gitHub.setRenamedAssignments(true);
                gitHub.setPinnedRepos(true);
                gitHub.setExternalLinks("Blocker" + i);
                gitHub.setImage(true);
                gitHub.setHeadline(true);
                gitHub.setImage(true);
                gitHub.setStudent(student1);
                gitHub.getStudent().setUid(student1Uid);
                gitHubRepo.save(gitHub);

                System.out.println("GitHub data created");
            }


            for (int i = 0; i < 4; i++) {
                Finalproject finalproject = new Finalproject();

                finalproject.setUid(student1Uid);
                finalproject.setProjectName("Blocker" + i);
                finalproject.setProposal("Blocker" + i);
                finalproject.setCrud("Blocker" + i);
                finalproject.setTables("Blocker" + i);
                finalproject.setViews("Blocker" + i);
                finalproject.setStudent(student1);
                finalproject.getStudent().setUid(student1Uid);
                finalprojectRepo.save(finalproject);

            }

            for (int i = 0; i < 4; i++) {
                LinkedIn linkedIn = new LinkedIn();
                linkedIn.setBanner(true);
                linkedIn.setAbout(true);
                linkedIn.setUrl("Blocker" + i);
                linkedIn.setFeaturedPosts(true);
                linkedIn.setActivity(true);
                linkedIn.setSkills(true);
                linkedIn.setEmail(true);
                linkedIn.setFirstName("Blocker" + i);
                linkedIn.setLastName("Blocker" + i);
                linkedIn.setBiography(true);
                linkedIn.setEducation(false);
                linkedIn.setExperience(true);
                linkedIn.setLocation(true);
                linkedIn.setImage(true);
                linkedIn.setTitle(true);
                linkedIn.setStudent(student1);
                linkedIn.getStudent().setUid(student1Uid);
                linkedInRepo.save(linkedIn);
            }

            for (int i = 0; i < 4; i++) {
                Networkingperson networkingperson = new Networkingperson();

                networkingperson.setUid(student1Uid);
                networkingperson.setTechStack("Blocker" + i);
                networkingperson.setFirstContactDate("Blocker" + i);
                networkingperson.setLastContactDate("Blocker" + i);
                networkingperson.setOtherNotesAboutPerson("Blocker" + i);
                networkingperson.setStudent(student1);
                networkingperson.getStudent().setUid(student1Uid);
                networkingpersonRepo.save(networkingperson);

                System.out.println("Networking Person data created");
            }

            for (int i = 0; i < 4; i++) {
                Networkingresource networkingresource = new Networkingresource();

                networkingresource.setUid(student1Uid);
                networkingresource.setResourceName("Blocker" + i);
                networkingresource.setStudent(student1);
                networkingresource.getStudent().setUid(student1Uid);
                networkingresourceRepo.save(networkingresource);

                System.out.println("Networking resource data created");
            }

            for (int i = 0; i < 4; i++) {
                Resume resume = new Resume();
                resume.setPhoneNumber("Blocker" + i);
                resume.setEmail("Blocker" + i);
                resume.setCity("Blocker" + i);
                resume.setState("Blocker" + i);
                resume.setLinkedIn("Blocker" + i);
                resume.setGitHub("Blocker" + i);
                resume.setSummary("Blocker" + i);
                resume.setSkills("Blocker" + i);
                resume.setWorkExperience("Blocker" + i);
                resume.setEducation("Blocker" + i);
                resume.setProjects("Blocker" + i);
                resume.setStudent(student1);
                resume.getStudent().setUid(student1Uid);
                resumeRepo.save(resume);
            }
        }
    }
}