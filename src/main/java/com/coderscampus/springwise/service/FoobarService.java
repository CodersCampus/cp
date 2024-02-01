package com.coderscampus.springwise.service;

import com.coderscampus.springwise.domain.Foobar;
import com.coderscampus.springwise.domain.Student;
import com.coderscampus.springwise.repository.FoobarRepository;
import com.coderscampus.springwise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoobarService {

	@Autowired
	private FoobarRepository foobarRepo;

	@Autowired
	private StudentRepository studentRepo;

	public Foobar save(Foobar foobar) {
		System.out.println("FoobarService.java save method| Foobar is: " + foobar);
		return foobarRepo.save(foobar);
	}

	public Foobar saveByUid(Foobar foobar, String uid) {
		List<Student> students = studentRepo.findByUid(uid);
		if(students.size()>1)
			throw new IllegalStateException("Shouldn't have more than one student per uid");
		if (!students.isEmpty()) {
			Student student = students.get(0);
			foobar.setStudent(student);
			foobar.setUid(uid);
			System.out.println("FoobarService.java| #1 - Foobar UID is: " + uid);
		}
		System.out.println("FoobarService.java| #2 - Foobar UID is: " + uid);
		return foobarRepo.save(foobar);
	}

	public List<Foobar> findAll() {
		return foobarRepo.findAll();
	}

	public Foobar findById(Long id) {
		return foobarRepo.findById(id).get();
	}

	public void delete(Foobar foobar) {
		foobarRepo.delete(foobar);
	}

}