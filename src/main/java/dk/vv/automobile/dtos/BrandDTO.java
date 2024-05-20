package dk.vv.automobile.dtos;

import dk.vv.automobile.entities.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class BrandDTO {

    private int id;

    private String name;
    private String description;

    public BrandDTO(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.description = brand.getDescription();
    }

    public BrandDTO(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
