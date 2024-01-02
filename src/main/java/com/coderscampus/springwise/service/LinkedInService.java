package com.coderscampus.springwise.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.LinkedIn;
import com.coderscampus.springwise.repository.LinkedInRepository;

@Service
public class LinkedInService {
	@Autowired
	private LinkedInRepository linkedInRepo;

	
	public LinkedIn save(LinkedIn linkedIn) {
		if (isValidNewLinkedIn(linkedIn)) {
			
			return linkedInRepo.save(linkedIn);
		}
		if (isValidLinkedInUpdateOrDelete(linkedIn)) {
			
			return linkedInRepo.save(linkedIn);
		}
		return null;
	}

	boolean isValidLinkedInUpdateOrDelete(LinkedIn linkedIn) {
		Optional<LinkedIn> existingLinkedIn = linkedInRepo.findById(linkedIn.getId());

		if (existingLinkedIn.isPresent() && existingLinkedIn.get().getId() != null
				&& existingLinkedIn.get().getId().equals(linkedIn.getId())) {
			
			return true;
		}
		return false;
	}

	boolean isValidNewLinkedIn(LinkedIn linkedIn) {
		Optional<LinkedIn> linkedIns = linkedInRepo.findById(linkedIn.getId());
		if (linkedIns.isEmpty()) {
			
			return false;
		}
		return linkedIn.getId() == 0;
	}

	public List<LinkedIn> findAll() {

		return linkedInRepo.findAll();
	}

	public LinkedIn findById(Long id) {
		return linkedInRepo.findById(id).get();
	}

	public boolean delete(LinkedIn linkedIn) {

		try {
			if (isValidLinkedInUpdateOrDelete(linkedIn)) {

				linkedInRepo.delete(linkedIn);

				Optional<LinkedIn> user = linkedInRepo.findById(linkedIn.getId());
				boolean foundUser = user.isPresent();
				if (foundUser) {
					throw new RuntimeErrorException(null, "User was not deleted");
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}

		return true;
	}
}
