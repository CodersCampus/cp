package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	public Student save(Student student) {
		/* We need to check for ID; if ID is present then it's an update otherwise it's a create. */ 
		return studentRepo.save(student);
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
