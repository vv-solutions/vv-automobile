package dk.vv.automobile.dtos;

import dk.vv.automobile.entities.Product;
import dk.vv.automobile.entities.ProductAvailability;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {
    private int id;
    private String name;
    private int brandId;

    private BigDecimal price;

    private String description;

    private String imgUrl;
    private int categoryId;

    private int productAvailabilityQuantity;

    private LocalDateTime createTimestamp;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brandId = product.getBrandId();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.imgUrl = product.getImgUrl();
        this.categoryId = product.getCategoryId();
        this.productAvailabilityQuantity = product.getProductAvailability().getQuantity();
        this.createTimestamp = product.getCreateTimestamp();
    }

    public ProductDTO() {
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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductAvailabilityQuantity() {
        return productAvailabilityQuantity;
    }

    public void setProductAvailabilityQuantity(int productAvailabilityQuantity) {
        this.productAvailabilityQuantity = productAvailabilityQuantity;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
