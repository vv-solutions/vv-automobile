package dk.vv.automobile.dtos;

import dk.vv.automobile.entities.Product;
import dk.vv.automobile.entities.ProductAvailability;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class ProductAvailabilityDTO {
    private int id;

    private int quantity;
    private ProductDTO product;

    public ProductAvailabilityDTO(ProductAvailability productAvailability) {
        this.id = productAvailability.getId();
        this.quantity = productAvailability.getQuantity();
        this.product = new ProductDTO(productAvailability.getProduct());
    }

    public ProductAvailabilityDTO(int id, int quantity){
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
