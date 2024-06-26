package dk.vv.automobile.facades;

import dk.vv.automobile.dtos.BrandDTO;
import dk.vv.automobile.dtos.OrderDTO;
import dk.vv.automobile.dtos.OrderLineDTO;
import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.entities.Brand;
import dk.vv.automobile.entities.Order;
import dk.vv.automobile.entities.OrderLine;
import dk.vv.automobile.entities.Product;
import dk.vv.automobile.errorhandling.ProductInactiveException;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderFacade {

    private final EntityManager slaveEntityManager;
    private final EntityManager masterEntityManager;

    @Inject
    public OrderFacade(EntityManager slaveEntityManager, EntityManager masterEntityManager) {
        this.slaveEntityManager = slaveEntityManager;
        this.masterEntityManager = masterEntityManager;
    }


    public OrderDTO createOrder(OrderDTO orderDTO) throws ProductInactiveException {

        Order order = new Order(orderDTO);


        List<Integer> productIds = orderDTO.getOrderLines().stream().map(OrderLineDTO::getProductId).toList();

        List<Product> products = masterEntityManager.createQuery("select p from Product p where p.id in :pids",Product.class)
                .setParameter("pids",productIds)
                .getResultList();

        for (Product product : products) {
            if(!product.isActive()){
                throw new ProductInactiveException(String.format("Product with product no. [%d], is no longer available",product.getId()));
            }
        }

        BigDecimal totalPrice = BigDecimal.ZERO;


        for (OrderLineDTO orderLineDTO : orderDTO.getOrderLines()) {
            OrderLine orderLine = new OrderLine();

            var product = products.stream().filter(p -> p.getId() == orderLineDTO.getProductId()).findFirst().get();

            // set unit price and linePrice
            var linePrice = new BigDecimal(orderLineDTO.getQuantity()).multiply(product.getPrice());

            orderLine.setLinePrice(linePrice);
            orderLine.setUnitPrice(product.getPrice());

            // set other properties
            orderLine.setQuantity(orderLineDTO.getQuantity());
            orderLine.setProductId(product.getId());


            order.addOrderLine(orderLine);


            // add line price to total price
            totalPrice = totalPrice.add(linePrice);
        }


        order.setTotalPrice(totalPrice);

        // Save order
        masterEntityManager.persist(order);

        masterEntityManager.flush();
        masterEntityManager.refresh(order);

        return new OrderDTO(order);
    }
    public List<OrderDTO> getAll(int count, int page){
        return slaveEntityManager.createQuery("Select new dk.vv.automobile.dtos.OrderDTO(o) from Order o order by id desc", OrderDTO.class)
                .setMaxResults(count)
                .setFirstResult(count*page)
                .getResultList();
    }


    public OrderDTO getOrderById(int id){
        return new OrderDTO(slaveEntityManager.find(Order.class,id));
    }

    public List<OrderDTO> searchOrders(String search){
        return slaveEntityManager.createQuery("Select new dk.vv.automobile.dtos.OrderDTO(o) from Order o " +
                        " where lower(o.email) LIKE :search or lower(o.phone) like :search or lower(str(o.id)) like :search", OrderDTO.class)
                .setParameter("search","%"+search.toLowerCase()+"%")
                .getResultList();
    }

}
