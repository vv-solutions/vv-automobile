package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.BrandDTO;
import dk.vv.automobile.dtos.OrderDTO;
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
}
