package dk.vv.automobile.entities;

import jakarta.persistence.*;

@Table(name = "product_availability")
@Entity
public class ProductAvailability {

    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        product = product;
    }
}
