package com.mandis.desafio_anota_ai.domain.category.product;

import com.mandis.desafio_anota_ai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter @Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price; //will multiply decimal by 100 before saving on database
    private Category category;
}
