package com.coderscampus.cp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.cp.domain.Work;
import com.coderscampus.cp.repository.WorkRepository;

@Service
public class WorkService {

	private final WorkRepository workRepository;

	@Autowired
	public WorkService(WorkRepository workRepository) {
		this.workRepository = workRepository;
	}

	public Work saveWork(Work work) {
		return workRepository.save(work);
	}

	public Optional<Work> findWorkById(Long id) {
		return workRepository.findById(id);
	}

    public List<Work> findAllWorks() {
        return workRepository.findAll();
    }
}
