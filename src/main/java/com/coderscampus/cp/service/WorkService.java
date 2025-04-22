package com.coderscampus.cp.service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
        return workRepository.findAllByOrderByDateDesc();
    }

    public Integer getAggregateMinutes(String studentName, Instant referenceDate) {
        Instant endDate = referenceDate;
        Instant startDate = referenceDate.minus(7, ChronoUnit.DAYS);
        return workRepository.getAggregateMinutes(studentName, startDate, endDate);
    }
}
