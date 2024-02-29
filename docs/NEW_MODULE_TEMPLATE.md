# New Object Template

This is a basic template based off of the Checkin Module. We are not 100% done with Checkin so this is not quite 100% a Template.
Use carefully.

## Domain

### `domain/Object`

```Java
import jakarta.persistence.Entity;

@Entity
public class ModuleNameHere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ADD INSTANCE VARIABLES HERE
    
}

// ADD GETTERS AND SETTERS HERE

// ADD ENUMS HERE (IF NEEDED)

// ADD TO STRING HERE

```

## Repository

### `repository/ObjectRepository`

```Java
package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<ModuleNameHere, Long> {
}

```


## Service

### `service/ObjectService`
- Use `service/CheckinService` as a resource not a template

```Java
package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Checkin save(Checkin checkin) {
        if (checkin.getDate() == null) {
            checkin.setDate(LocalDateTime.now());
        }
        return checkinRepo.save(checkin);
    }

    public Checkin saveByUid(Checkin checkin, String uid) {
        List<Student> students = studentRepo.findByUid(uid);
        if (students.size() > 1)
            throw new IllegalStateException("Shouldn't have more than one student per uid");
        if (!students.isEmpty()) {
            Student student = students.get(0);
            checkin.setStudent(student);
            checkin.setUid(uid);
        }
        return checkinRepo.save(checkin);
    }

    public List<Checkin> findAll() {
        return checkinRepo.findAll().stream().sorted(Comparator.comparing(Checkin::getDate).reversed()).collect(Collectors.toList());
    }

    public Checkin findById(Long id) {
        return checkinRepo.findById(id).get();
    }

    public void delete(Checkin checkin) {
        checkinRepo.delete(checkin);
    }

}

```

## Web

### `web/ModuleNameHereService`
- Use `web/CheckinController` as a resource not a template

```Java
package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @GetMapping("/")
    public String home(ModelMap model) {
        List<Checkin> checkins = checkinService.findAll();
        model.put("checkins", checkins);
        model.addAttribute("pageTitle", "Checkin Read");
        model.put("isCheckin", true);
        return "checkin/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        Checkin checkin = new Checkin();
        model.put("checkin", checkin);
        model.addAttribute("pageTitle", "Checkin Create");
        model.put("isCheckin", true);
        return "checkin/create";
    }

    @PostMapping("/create")
    public String create(Checkin checkin, @RequestParam("uid") String uid) {
        checkin = checkinService.saveByUid(checkin, uid);
        return "redirect:/checkin/";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        Checkin checkin = checkinService.findById(id);
        model.put("checkin", checkin);
        model.addAttribute("pageTitle", "Checkin Update");
        model.put("isCheckin", true);
        return "checkin/update";
    }

    @PostMapping("/update")
    public String update(Checkin checkin) {
        checkinService.save(checkin);
        return "redirect:/checkin/";
    }

    @PostMapping("/delete")
    public String delete(Checkin checkin) {
        checkinService.delete(checkin);
        return "redirect:/checkin/";
    }

    @ModelAttribute("roleList")
    public Checkin.Role[] getRoleList() {
        return Checkin.Role.values();
    }

    @ModelAttribute("codingType")
    public Checkin.CodingType[] getCodingType() {
        return Checkin.CodingType.values();
    }
}


```

## Templates

### templates/moduleNameHere/read.html
- Use `checkin/read.html` as a resource, not a template

```HTML 
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org">
<div th:replace="~{fragments/head :: head(${pageTitle})}"></div>

<body layout="layout-secured">
<fbauth-element>
    <div layout="layout-sidebar">
        <header th:replace="~{fragments/header :: header(${activePage})}"></header>
        <div class="sidebar-grid-area">
            <nav class="navbar navbar-expand-lg navbar-light">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="/checkin/create">Create Checkin</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="ga-content-main">
            <main>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Next Assignment:</th>
                        <th scope="col">Blockers:</th>
                        <th scope="col">Blocker Description:</th>
                        <th scope="col">Box Ready for Live Coding?:</th>
                        <th scope="col">Are You Available/willing to Code?:</th>
                        <th scope="col">Role:</th>
                        <th scope="col">Coding Type:</th>
                        <th scope="col">Issue Number:</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr th:each="checkin: ${checkins}">
                        <td><span th:text="${checkin.assignment}"></span></td>
                        <td><span th:text="${checkin.blockers}"></span></td>
                        <td><span th:text="${checkin.blockerDescription}"></span></td>
                        <td><span th:text="${checkin.isSetUp}"></span></td>
                        <td><span th:text="${checkin.available}"></span></td>
                        <td><span th:text="${checkin.role}"></span></td>
                        <td><span th:text="${checkin.codingType}"></span></td>
                        <td><span th:text="${checkin.issueNumber}"></span></td>
                        <td><a th:href="@{/checkin/update/{checkinId}(checkinId=${checkin.id})}">
									<span><svg xmlns="http://www.w3.org/2000/svg" width="16"
                                               height="16" fill="currentColor" class="bi bi-view-list"
                                               viewBox="0 0 16 16">
  <path
          d="M3 4.5h10a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-3a2 2 0 0 1 2-2zm0 1a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1H3zM1 2a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 0 1h-13A.5.5 0 0 1 1 2zm0 12a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 0 1h-13A.5.5 0 0 1 1 14z"/>
</svg></span>
                        </a></td>
                    </tr>
                    </tbody>
                </table>
            </main>
        </div>
        <div th:replace="~{fragments/footer :: footer}"></div>
    </div>
</fbauth-element>
<script>
    const fbauth = document.querySelector('fbauth-element');
    const uid = document.getElementById('uid');
    console.log("UID: " + uid);
    const displayName = document.getElementById('display-name');
    fbauth.addEventListener('user-changed', (event) => {
        const {newValue} = event.detail;
        console.log("SETTING UID TO " + newValue.uid + " AND DISPLAY NAME TO " + newValue.displayName);
        if (newValue) {
            uid.value = newValue.uid;
        } else {
            uid.value = '';
        }
        if (newValue) {
            displayName.value = newValue.displayName;
        } else {
            displayName.value = '';
        }
    });
</script>
</body>
</html>

```

### templates/moduleNameHere/create.html
- Use `checkin/create.html` as a resource, not a template

```HTML
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org">
<div th:replace="~{fragments/head :: head(${pageTitle})}"></div>

<body layout="layout-secured">
<fbauth-element>
    <div layout="layout-sidebar">
        <header th:replace="~{fragments/header :: header(${activePage})}"></header>
        <div class="sidebar-grid-area">
            <nav class="navbar navbar-expand-lg navbar-light">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="/checkin/">Checkin
                            Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="ga-content-main">
            <main>
                <form method="post" action="/checkin/create">
                    <h1>Create Checkin</h1>
                    <input name="uid" type="hidden" id="uid"/>
                    <div class="mb-3">
                        <input class="form-control" hidden type="text" th:field="${checkin.date}"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Next Assignment:</label>
                        <input class="form-control" type="number" th:field="${checkin.assignment}"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Blockers:</label>
                        <input type="checkbox" th:field="${checkin.blockers}"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Blocker Description:</label>
                        <input type="text" th:field="${checkin.blockerDescription}"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Box Ready for Live Coding?:</label>
                        <input type="checkbox" th:field="${checkin.isSetUp}"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Are You Available/willing to Code?:</label>
                        <input type="checkbox" th:field="${checkin.available}"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Role:</label>
                        <select class="form-control" th:field="*{checkin.role}">
                            <option th:each="role : ${roleList}" th:value="${role}"
                                    th:text="${#strings.capitalize(role.toString())}">
                            </option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Coding Type:</label>
                        <select class="form-control" th:field="*{checkin.codingType}">
                            <option th:each="codingType : ${codingType}" th:value="${codingType}"
                                    th:text="${#strings.capitalize(codingType.toString())}">
                            </option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Issue Number:</label>
                        <input class="form-control" type="number" th:field="${checkin.issueNumber}"/>
                    </div>
                    <div class="mb-3">
                        <input class="form-control btn btn-primary" type="submit" value="Create"/>
                    </div>
                    <div class="mb-3">
                        <input class="form-control btn btn-secondary" type="" value="Start Time/End Time"/>
                    </div>
                </form>
            </main>
        </div>
        <div th:replace="~{fragments/footer :: footer}"></div>
    </div>
</fbauth-element>
<script>
    const fbauth = document.querySelector('fbauth-element');
    const uid = document.getElementById('uid');
    console.log("UID: " + uid);
    const displayName = document.getElementById('display-name');
    fbauth.addEventListener('user-changed', (event) => {
        const {newValue} = event.detail;
        console.log("SETTING UID TO " + newValue.uid + " AND DISPLAY NAME TO " + newValue.displayName);
        if (newValue) {
            uid.value = newValue.uid;
        } else {
            uid.value = '';
        }
        if (newValue) {
            displayName.value = newValue.displayName;
        } else {
            displayName.value = '';
        }
    });
</script>
</body>
</html>
```


### templates/moduleNameHere/update.html
- Use `checkin/update.html` as a resource, not a template

```HTML
<!DOCTYPE html>
<html xmlns:th="http://thymeleaf.org">
<div th:replace="~{fragments/head :: head(${pageTitle})}"></div>

<body layout="layout-secured">
	<fbauth-element>
	<div layout="layout-sidebar">
		<header th:replace="~{fragments/header :: header(${activePage})}"></header>
		<div class="sidebar-grid-area">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/checkin/">Checkin Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/checkin/create">Create Checkin</a></li>
					</ul>
				</div>
			</nav>
		</div>
		<div class="ga-content-main">
			<main>
				<form method="post" action="/checkin/update" class="mb-3">
					<input class="form-control" type="hidden" th:field="${checkin.id}" />
					<label class="form-label">Date: </label> <input
						class="form-control" type="text" th:field="${checkin.date}"
						disabled /> <br> <label class="form-label">Assignment:
					</label> <input class="form-control" type="number"
						th:field="${checkin.assignment}" /> <br> <label
						class="form-label">Blockers: </label> <input type="checkbox"
						th:field="${checkin.blockers}" /> <br> <label
						class="form-label">SpringWise: </label> <input type="checkbox"
						th:field="${checkin.springWise}" /> <br> <label
						class="form-label">Available: </label> <input type="checkbox"
						th:field="${checkin.available}" /> <br> <input
						class="form-control btn btn-primary mt-3" type="submit"
						value="Update" />
				</form>
				<form method="post" action="/checkin/delete" class="mb-3">
					<input type="hidden" th:field="${checkin.id}" /> <input
						class="form-control btn btn-danger" type="submit" value="Delete" />
				</form>
			</main>
		</div>
		<div th:replace="~{fragments/footer :: footer}"></div>
	</div>
	</fbauth-element>
    <script>
        const fbauth = document.querySelector('fbauth-element');
        const uid = document.getElementById('uid');
        console.log("UID: " + uid);
        const displayName = document.getElementById('display-name');
        fbauth.addEventListener('user-changed', (event) => {
            const {newValue} = event.detail;
            console.log("SETTING UID TO " + newValue.uid + " AND DISPLAY NAME TO " + newValue.displayName);
            if (newValue) {
                uid.value = newValue.uid;
            } else {
                uid.value = '';
            }
            if (newValue) {
                displayName.value = newValue.displayName;
            } else {
                displayName.value = '';
            }
        });
    </script>
</body>

</html>



```