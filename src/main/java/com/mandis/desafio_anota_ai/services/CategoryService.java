package com.mandis.desafio_anota_ai.services;

import com.mandis.desafio_anota_ai.domain.category.Category;
import com.mandis.desafio_anota_ai.domain.category.CategoryDto;
import com.mandis.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.mandis.desafio_anota_ai.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category insert(CategoryDto categoryDto) {
        Category newCategory = new Category(categoryDto);
        return this.repository.save(newCategory);
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id) {
        return this.repository.findById(id);
    }

    public Category update(String categoryId, CategoryDto categoryDto) {
        Category category = this.repository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryDto.title().isEmpty()) category.setTitle(categoryDto.title());
        if (!categoryDto.description().isEmpty()) category.setDescription(categoryDto.description());

        return category;
    }

    public void delete(String categoryId) {
        Category category = this.repository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }
}
