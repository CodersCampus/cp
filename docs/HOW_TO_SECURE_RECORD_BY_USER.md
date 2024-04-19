# How to Secure Record by User

## Objective

- [Set UID on `Create`](#how-to-send-uid-on-create)
    - Establish the first linkage of your record to your persona
- [Validate UID on `Update`](#how-to-send-uid-on-update-form)
    - To make sure you are who you say you are before you perform any opperation
- Never send UID from backend to frontend
    - Can not be mocked on the frontend side within dev tools
- Always send UID from frontend to backend

## How to send UID on Create

```HTML 
<input type="hidden" id="new-uid" th:field="${student.uid}" />
```

- This goes straight into the database as a normal Thymeleaf field (`th:field`)

## How to send UID on Update form

> Three steps need to happen here

### Step 1: Thymeleaf

```HTML
<input type="hidden" id="uid" name="firebaseUid" />
```

> _updated on 1/18/2024_

### Step2: Controller

```JAVA
@PostMapping("/update")
public String update(Student student, @RequestParam("uid") String firebaseUid)
```

> _updated on 1/18/2024_

### Step 3: Service

Look at `StudentService.java` and inside look at the methods `isValidStudentUpdateDelete`,
`isValidNewStudent`, and `delete`.
> _Reference the code below as a guide_

```JAVA
public Student save(Student student) {
  if (isValidNewStudent(student)) {

    return studentRepo.save(student);
  }
  if (isValidStudentUpdateOrDelete(student)) {

    return studentRepo.save(student);
  }
  return null;
}
```

## Unit Test

- Use Unit Testing for example Student Service Test to test all new methods

## Q & A

Q: Why don't we have to worry about user stuff if backend is serving up all the HTML?

A: If it's coming from server it's not accessible via dev tools. This would be safe data/clean data: 
