package dk.vv.automobile.entities.types;

import dk.vv.automobile.dtos.OrderLineDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Struct;

import java.io.Serializable;

@Struct(name = "product_quantity_pair")
@Embeddable
public class ProductQuantityPair implements Serializable {

    @Column(name = "p_id")
    private int productId;
    @Column(name = "p_quantity")
    private int quantity;

    // Constructors
    public ProductQuantityPair() {}

    public ProductQuantityPair(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductQuantityPair(OrderLineDTO orderLineDTO) {
        this.productId = orderLineDTO.getProductId();
        this.quantity = orderLineDTO.getQuantity();
    }

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
