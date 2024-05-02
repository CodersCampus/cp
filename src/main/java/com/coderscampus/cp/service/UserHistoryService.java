package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.UserHistory;
import com.coderscampus.cp.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserHistoryService {

    private final UserHistoryRepository userHistoryRepo;

    @Autowired
    public UserHistoryService(UserHistoryRepository userHistoryRepo) {
        this.userHistoryRepo = userHistoryRepo;
    }

    public UserHistory save(UserHistory userHistory) {
        if (userHistory.getDate() == null) {
            userHistory.setDate(Instant.now());
        }
        return userHistoryRepo.save(userHistory);
    }

    public List<UserHistory> findAll() {
        return userHistoryRepo.findAll().stream().sorted(Comparator.comparing(UserHistory::getDate).reversed())
                .collect(Collectors.toList());
    }

    public UserHistory findById(Long id) {
        return userHistoryRepo.findById(id).get();
    }

    public void delete(UserHistory userHistory) {
        userHistoryRepo.delete(userHistory);
    }
}
