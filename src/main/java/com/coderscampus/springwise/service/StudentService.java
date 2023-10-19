package com.coderscampus.springwise.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	// before: if (isValidNewStudent(student) || isValidStudentUpdate(student)) -> save student)
	public Student save(Student student) {
		if (isValidNewStudent(student)) {
			System.out.println("This is isValidNewStudent" + student);
			return studentRepo.save(student); 
		}
		if(isValidStudentUpdate(student) ) {
			System.out.println("This is isValidStudentUpdate" + student);
			return studentRepo.save(student);
		}
		return null;
	}

	boolean isValidStudentUpdate(Student student) {
		Optional<Student> existingStudent = studentRepo.findById(student.getId());

		if (existingStudent.isPresent() && existingStudent.get().getUid() != null && existingStudent.get().getUid().equals(student.getUid())) {
			System.out.println("This is existingStudent" + student);
			return true;
		}
		return false;
	}

	boolean isValidNewStudent(Student student) {
		// First test is id == 0 means to create:
		List<Student> students = studentRepo.findByUid(student.getUid());
		if (students.size() > 0) {
			System.out.println("These are the students " + students);
			return false;
		}
		return student.getId() == 0; 
	}

	public List<Student> findAll() {

		return studentRepo.findAll();
	}

	public Student findById(Long id) {
		return studentRepo.findById(id).get();
	}

	public void delete(Student student) {
		studentRepo.delete(student);
	}

}
