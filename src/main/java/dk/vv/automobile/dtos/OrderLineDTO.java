package dk.vv.automobile.dtos;

import dk.vv.automobile.entities.Order;
import dk.vv.automobile.entities.OrderLine;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class OrderLineDTO {
    int id;
    private int productId;
    private int quantity;
    private BigDecimal linePrice;
    private BigDecimal unitPrice;


    public OrderLineDTO() {
    }

    public OrderLineDTO(OrderLine orderLine) {
        this.id = orderLine.getId();
        this.productId = orderLine.getProductId();
        this.quantity = orderLine.getQuantity();
        this.linePrice = orderLine.getLinePrice();
        this.unitPrice = orderLine.getUnitPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


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

    public BigDecimal getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
