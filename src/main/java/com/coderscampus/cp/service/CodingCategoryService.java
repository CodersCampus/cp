package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.CodingCategory;
import com.coderscampus.cp.repository.CodingCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingCategoryService {

    private final CodingCategoryRepository codingCategoryRepo;

    @Autowired
    public CodingCategoryService(CodingCategoryRepository codingCategoryRepo) {
        this.codingCategoryRepo = codingCategoryRepo;
    }

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
