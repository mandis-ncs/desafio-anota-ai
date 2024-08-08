package com.mandis.desafio_anota_ai.services;

import com.mandis.desafio_anota_ai.domain.category.Category;
import com.mandis.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.mandis.desafio_anota_ai.domain.product.Product;
import com.mandis.desafio_anota_ai.domain.product.ProductDto;
import com.mandis.desafio_anota_ai.domain.product.exceptions.ProductNotFoundException;
import com.mandis.desafio_anota_ai.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    public final CategoryService categoryService;

    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Product insert(ProductDto productDto) {
        Category category = this.categoryService.getById(productDto.categoryId()).orElseThrow(
                CategoryNotFoundException::new);
        Product newProduct = new Product(productDto);
        newProduct.setCategory(category);
        return this.repository.save(newProduct);
    }


    public Product update(String id, ProductDto productDto) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if (productDto.categoryId() != null)
            this.categoryService.getById(productDto.categoryId())
                    .ifPresent(product::setCategory);

        if (!productDto.title().isEmpty()) product.setTitle(productDto.title());
        if (!productDto.description().isEmpty()) product.setDescription(productDto.description());
        if (!(productDto.price() == null)) product.setPrice(productDto.price());

        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }



}
