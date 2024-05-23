package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.BrandDTO;
import dk.vv.automobile.dtos.OrderDTO;
import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.facades.BrandFacade;
import dk.vv.automobile.facades.OrderFacade;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;

@ApplicationScoped
@Resource
@Path("/order/")
@Produces("application/json")
@Consumes("application/json")
public class OrderResource {


    private final OrderFacade orderFacade;

    @Inject
    public OrderResource(OrderFacade orderFacade) {
        this.orderFacade= orderFacade;
    }

    @POST
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO){
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
