package dk.vv.automobile.entities;

import dk.vv.automobile.dtos.BrandDTO;
import jakarta.persistence.*;

import java.util.Set;

@Table(name = "brand")
@Entity
public class Brand {

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "brand_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_seq")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Brand() {
    }

    public Brand(BrandDTO brandDTO) {
        this.name = brandDTO.getName();
        this.description = brandDTO.getDescription();
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
