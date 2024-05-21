package dk.vv.automobile.dtos;

import dk.vv.automobile.entities.Order;
import dk.vv.automobile.entities.OrderLine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderDTO {
    private int id;
    private BigDecimal totalPrice;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String street;
    private int houseNumber;

    private String addressLine2;

    private int zipcode;

    private LocalDateTime create;
    private List<OrderLineDTO> orderLines = new ArrayList<>();


    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.firstName = order.getFirstName();
        this.lastName = order.getLastName();
        this.phone = order.getPhone();
        this.email = order.getEmail();
        this.street = order.getStreet();
        this.houseNumber = order.getHouseNumber();
        this.addressLine2 = order.getAddressLine2();
        this.zipcode = order.getZipcode();
        this.create = order.getCreate();
        for (OrderLine orderLine : order.getOrderLines()) {
            this.orderLines.add(new OrderLineDTO(orderLine));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create;
    }

    public void addOrderLine(OrderLineDTO orderLineDTO){
        this.orderLines.add(orderLineDTO);
    }

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }
}
