package com.mandis.desafio_anota_ai.controllers;

import com.mandis.desafio_anota_ai.domain.category.Category;
import com.mandis.desafio_anota_ai.domain.category.CategoryDto;
import com.mandis.desafio_anota_ai.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDto categoryDto){
        Category newCategory = this.service.insert(categoryDto);
        return ResponseEntity.ok().body(newCategory);
    }


    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDto categoryDto) {
        Category updatedCategory = this.service.update(id, categoryDto);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
