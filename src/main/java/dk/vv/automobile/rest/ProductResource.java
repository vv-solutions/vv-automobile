package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.facades.ProductFacade;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@ApplicationScoped
@Resource
@Path("/product/")
@Produces("application/json")
@Consumes("application/json")
public class ProductResource {


    private final ProductFacade productFacade;

    @Inject
    public ProductResource(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GET
    @Path("/{id}")
    public ProductDTO getById(@PathParam("id") int id){
        return productFacade.getProductById(id);
    }

    @GET
    @Path("/category")
    public List<ProductDTO> getProductsByCategories(@QueryParam("category") int category, @QueryParam("count") int count, @QueryParam("page") int page, @QueryParam("brands") List<Integer> brands){
        return productFacade.getProductsWithPaginationByCategory(count,page,category,brands);
    }


}
