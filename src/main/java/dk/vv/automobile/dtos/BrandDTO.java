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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
