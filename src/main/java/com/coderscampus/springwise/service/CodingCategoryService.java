package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.CodingCategory;
import com.coderscampus.springwise.repository.CodingCategoryRepository;

@Service
public class CodingCategoryService {

	@Autowired
	private CodingCategoryRepository codingCategoryRepo;

	public CodingCategory save(CodingCategory codingCategory) {
		return codingCategoryRepo.save(codingCategory);
	}

	public List<CodingCategory> findAll() {

		return codingCategoryRepo.findAll();
	} 

	public CodingCategory findById(Long id) {
		return codingCategoryRepo.findById(id).get();
	}

	public void delete(CodingCategory codingCategory) {
		codingCategoryRepo.delete(codingCategory);
	}

}
