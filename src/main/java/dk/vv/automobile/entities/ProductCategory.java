package dk.vv.automobile.entities;

import dk.vv.automobile.dtos.ProductCategoryDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "product_category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_seq")
    private int id;

    @Column(name = "name")
    private String name;

    public ProductCategory() {
    }

    public ProductCategory(ProductCategoryDTO productCategoryDTO) {
        this.name = productCategoryDTO.getName();
    }

    public ProductCategory(int id) {
        this.id = id;
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
