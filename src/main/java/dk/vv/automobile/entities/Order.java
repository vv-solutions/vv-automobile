package dk.vv.automobile.entities;

import dk.vv.automobile.dtos.OrderDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"order\"")

public class Order {

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_seq")
    private int id;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private int houseNumber;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "create_timestamp")
    @CreationTimestamp
    private LocalDateTime create;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines = new HashSet<>();

    public Order() {
    }


    public Order(OrderDTO orderDTO) {
        this.firstName = orderDTO.getFirstName();
        this.lastName = orderDTO.getLastName();
        this.phone = orderDTO.getPhone();
        this.email = orderDTO.getEmail();
        this.street = orderDTO.getStreet();
        this.houseNumber = orderDTO.getHouseNumber();
        this.addressLine2 = orderDTO.getAddressLine2();
        this.zipcode = orderDTO.getZipcode();
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

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void addOrderLine(OrderLine orderLine){
        this.orderLines.add(orderLine);

        if(orderLine.getOrder() != this){
            orderLine.setOrder(this);
        }
    }
}
