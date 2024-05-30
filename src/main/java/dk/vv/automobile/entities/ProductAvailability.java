package dk.vv.automobile.entities;

import dk.vv.automobile.entities.types.ProductQuantityPair;
import jakarta.persistence.*;

@Table(name = "product_availability")
@Entity
public class ProductAvailability {

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "product_availability_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_seq")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductAvailability() {
    }
    public ProductAvailability(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
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
