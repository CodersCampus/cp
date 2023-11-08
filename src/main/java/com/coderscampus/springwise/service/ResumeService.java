package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Resume;
import com.coderscampus.springwise.repository.ResumeRepository;

@Service
public class ResumeService {

	@Autowired
	private ResumeRepository resumeRepo;

	public Resume save(Resume resume) {
		return resumeRepo.save(resume);
	}

	public List<Resume> findAll() {

		return resumeRepo.findAll();
	}

	public Resume findById(Long id) {
		return resumeRepo.findById(id).get();
	}

	public void delete(Resume resume) {
		resumeRepo.delete(resume);
	}

}
