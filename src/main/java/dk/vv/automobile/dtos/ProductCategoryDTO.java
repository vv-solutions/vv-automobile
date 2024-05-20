package dk.vv.automobile.dtos;

import dk.vv.automobile.entities.ProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class ProductCategoryDTO {
    private int id;
    private String name;


    public ProductCategoryDTO(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
