package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.OrderDTO;
import dk.vv.automobile.entities.types.ProductQuantityPair;
import dk.vv.automobile.errorhandling.ProductQuantityException;
import dk.vv.automobile.facades.OrderFacade;
import dk.vv.automobile.facades.ProductFacade;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Resource
@Path("/order/")
@Produces("application/json")
@Consumes("application/json")
public class OrderResource {


    private final OrderFacade orderFacade;

    private  final ProductFacade productFacade;

    @Inject
    public OrderResource(OrderFacade orderFacade, ProductFacade productFacade) {
        this.orderFacade= orderFacade;
        this.productFacade = productFacade;
    }

    @POST
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) throws ProductQuantityException {

        List<ProductQuantityPair> pairs = orderDTO.getOrderLines().stream().map(ProductQuantityPair::new).collect(Collectors.toList());
        productFacade.decreaseProductAvailability(pairs);
        return orderFacade.createOrder(orderDTO);
    }

    @GET
    @Path("/all/{count}/{page}")
    public List<OrderDTO> getAll(@PathParam("count") int count, @PathParam("page") int page){
        return orderFacade.getAll(count,page);
    }

    @GET
    @Path("/{id}")
    public OrderDTO getAll(@PathParam("id") int id){
        return orderFacade.getOrderById(id);
    }

    @GET
    @Path("/search")
    public List<OrderDTO> searchProducts(@QueryParam("query") String search){
        return orderFacade.searchOrders(search);
    }

}
