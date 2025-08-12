package com.coderscampus.cp.manual;

import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.GitHubRepository;
import com.coderscampus.cp.repository.StudentRepository;
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

    void prepData() throws MalformedURLException {

        studentDTO1 = new StudentDTO();
        studentDTO2 = new StudentDTO();

        student1Uid = UUID.randomUUID().toString();
        student2Uid = UUID.randomUUID().toString();

        student1 = new Student(student1Uid, "name1", 1, "IntelliJ", false, "mentor1", null);
        student2 = new Student(student2Uid, "name2", 2, "IntelliJ", false, "mentor2", null);

        student1 = studentRepo.save(student1);
        student2 = studentRepo.save(student2);

        student1GitHubList = new ArrayList<>();

        List<StudentDTO> student1StudentDTOList = new ArrayList<StudentDTO>();

        for (int i = 0; i < 4; i++) {
            GitHub gitHub  = new GitHub();
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
            student1GitHubList.add(gitHub);

            System.out.println("GitHub data created");
        }
    }
}
