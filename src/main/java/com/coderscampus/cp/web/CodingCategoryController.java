package com.coderscampus.cp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.cp.domain.CodingCategory;
import com.coderscampus.cp.service.CodingCategoryService;

@Controller
@RequestMapping("coding-category")
public class CodingCategoryController {

    @Autowired
    private CodingCategoryService codingCategoryService;

    @GetMapping("/")
    public String home(ModelMap model) {
        List<CodingCategory> codingCategorys = codingCategoryService.findAll();
        model.put("codingCategorys", codingCategorys);
        return "coding-category/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        CodingCategory codingCategory = new CodingCategory();
        model.put("codingCategory", codingCategory);
        return "coding-category/create";
    }

    @PostMapping("/create")
    public String create(CodingCategory codingCategory) {
        codingCategoryService.save(codingCategory);
        return "redirect:/coding-category/";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id) {
        CodingCategory codingCategory = codingCategoryService.findById(id);
        model.put("codingCategory", codingCategory);
        return "coding-category/update";
    }

    @PostMapping("/update")
    public String update(CodingCategory codingCategory) {
        codingCategoryService.save(codingCategory);
        return "redirect:/coding-category/";
    }

    @PostMapping("/delete")
    public String delete(CodingCategory codingCategory) {
        codingCategoryService.delete(codingCategory);
        return "redirect:/coding-category/";
    }
}
