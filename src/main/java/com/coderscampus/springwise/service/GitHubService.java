package com.coderscampus.springwise.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.GitHub;
import com.coderscampus.springwise.repository.GitHubRepository;

@Service
public class GitHubService {
	@Autowired
	private GitHubRepository gitHubRepo;

	public GitHub save(GitHub gitHub) {
		return gitHubRepo.save(gitHub);
	}

	public List<GitHub> findAll() {

		return gitHubRepo.findAll();
	}

	public GitHub findById(Long id) {
		return gitHubRepo.findById(id).get();
	}

	public void delete(GitHub gitHub) {
		gitHubRepo.delete(gitHub);
	}


}
