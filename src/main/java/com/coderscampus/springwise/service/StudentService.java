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
		List<Student> students = studentRepo.findByUid(student.getUid());
		if (students.size() > 0 && student.getId() == 0) {

			return null;
		}
		Optional<Student> updateStudent = studentRepo.findById(student.getId());
		if (updateStudent.isPresent() && updateStudent.get().getUid().equals(student.getUid())) {

			return studentRepo.save(student);
		} else {
			return null;
		}

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
