package dk.vv.automobile.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_popularity")
public class ProductPopularity {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "purchase_count")
    private int purchaseCount;

    public ProductPopularity() {
    }

    public int getProductId() {
        return productId;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }
}
