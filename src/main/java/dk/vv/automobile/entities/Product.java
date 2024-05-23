package dk.vv.automobile.entities;

import dk.vv.automobile.dtos.ProductDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_seq")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "category_id")
    private int categoryId;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductAvailability productAvailability;

    @Column(name = "create_timestamp")
    @CreationTimestamp
    private LocalDateTime createTimestamp;

    public Product() {
    }

    public Product(ProductDTO productDTO) {
        this.name = productDTO.getName();
        this.brandId = productDTO.getBrandId();
        this.price = productDTO.getPrice();
        this.description = productDTO.getDescription();
        this.imgUrl = productDTO.getImgUrl();
        this.categoryId = productDTO.getCategoryId();
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

    public ProductAvailability getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(ProductAvailability productAvailability) {
        this.productAvailability = productAvailability;
        if (productAvailability != null) {
            productAvailability.setProduct(this);
        }
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
