package com.coderscampus.springwise.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.YouTubeChannel;
import com.coderscampus.springwise.repository.YouTubeRepository;

@Service
public class YouTubeChannelService {
	@Autowired
	private YouTubeRepository youTubeRepo;

	public YouTubeChannel save(YouTubeChannel youTube) {
		return youTubeRepo.save(youTube);
	}

	public List<YouTubeChannel> findAll() {

		return youTubeRepo.findAll();
	}

	public YouTubeChannel findById(Long id) {
		return youTubeRepo.findById(id).get();
	}

	public void delete(YouTubeChannel youTube) {
		youTubeRepo.delete(youTube);
	}


}
