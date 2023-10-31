package com.coderscampus.springwise.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.UserHistory;
import com.coderscampus.springwise.repository.UserHistoryRepository;

@Service
public class UserHistoryService {

	@Autowired
	private UserHistoryRepository userHistoryRepo;

	public UserHistory save(UserHistory userHistory) {
		
		if(userHistory.getDate()== null) {
			userHistory.setDate(LocalDateTime.now());
			
		}
		return userHistoryRepo.save(userHistory);
	}

	public List<UserHistory> findAll() {

		
		
		return userHistoryRepo.findAll().stream().sorted(Comparator.comparing(UserHistory::getDate).reversed()).collect(Collectors.toList());
	
	}

	public UserHistory findById(Long id) {
		return userHistoryRepo.findById(id).get();
	}

	public void delete(UserHistory userHistory) {
		userHistoryRepo.delete(userHistory);
	}

}
