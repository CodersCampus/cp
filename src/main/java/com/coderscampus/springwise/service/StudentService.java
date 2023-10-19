package com.coderscampus.springwise.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;

	// before: if (isValidNewStudent(student) || isValidStudentUpdate(student)) ->
	// save student)
	public Student save(Student student) {
		if (isValidNewStudent(student)) {
			System.out.println("This is isValidNewStudent" + student);
			return studentRepo.save(student);
		}
		if (isValidStudentUpdateOrDelete(student)) {
			System.out.println("This is isValidStudentUpdate" + student);
			return studentRepo.save(student);
		}
		return null;
	}

	boolean isValidStudentUpdateOrDelete(Student student) {
		Optional<Student> existingStudent = studentRepo.findById(student.getId());
		Here is where we work
		if (existingStudent.isPresent() && existingStudent.get().getUid() != null
				&& existingStudent.get().getUid().equals(student.getUid())) {
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

	public boolean delete(Student student) {

		try {
			if (isValidStudentUpdateOrDelete(student)) {

				studentRepo.delete(student);

				Optional<Student> user = studentRepo.findById(student.getId());
				boolean foundUser = user.isPresent();
				if (foundUser) {
					throw new RuntimeErrorException(null, "User was not deleted");
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			return false;
		}

		return true;
	}

}
