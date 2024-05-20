package dk.vv.automobile.entities;

import jakarta.persistence.*;

@Table(name = "product_availability")
@Entity
public class ProductAvailability {

    @Id
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductAvailability() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        product = product;
    }
}
