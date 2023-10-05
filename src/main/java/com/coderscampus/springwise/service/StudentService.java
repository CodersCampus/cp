package com.coderscampus.springwise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	

	public Student save(Student student) {
		if (isValidNewStudent(student) || isValidStudentUpdate(student)) {
			return studentRepo.save(student); 
		}
		
		
		
		
//		List<Student> students = studentRepo.findByUid(student.getUid());
//		if (students.size() > 0 || student.getId() != 0) {
//			Optional<Student> updateStudent = studentRepo.findById(student.getId());
//			if (updateStudent.isPresent() && updateStudent.get().getUid().equals(student.getUid())) {
//// create new logic for new incoming students, work on update existing students
//				return studentRepo.save(student);
//	
//		}
//		if (student.getId() == 0) {
//			return studentRepo.save(student);
//		}
//		
//		
//		} else {
//			return null;
//		}
		return null;
	}

	boolean isValidStudentUpdate(Student student) {
		Optional<Student> existingStudent = studentRepo.findById(student.getId());
		if (existingStudent.isPresent() && existingStudent.get().getUid() != null && existingStudent.get().getUid().equals(student.getUid())) {
			return true;
		}
		return false;
	}

	boolean isValidNewStudent(Student student) {
		// First test is id == 0 means to create:
		List<Student> students = studentRepo.findByUid(student.getUid());
		if (students.size() > 0) {
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
