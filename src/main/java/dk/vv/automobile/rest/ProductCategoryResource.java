package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.BrandDTO;
import dk.vv.automobile.dtos.ProductCategoryDTO;
import dk.vv.automobile.facades.BrandFacade;
import dk.vv.automobile.facades.CategoryFacade;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;

@ApplicationScoped
@Resource
@Path("/category/")
@Produces("application/json")
@Consumes("application/json")
public class ProductCategoryResource {


    private final CategoryFacade categoryFacade;

    @Inject
    public ProductCategoryResource(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    @GET
    @Path("/{id}")
    public ProductCategoryDTO getById(@PathParam("id") int id){
        return categoryFacade.getCategoryById(id);
    }

    @GET
    @Path("/all")
    public List<ProductCategoryDTO> getAll(){
        return categoryFacade.getAll();
    }

    @PUT
    @Path("/")
    @Transactional
    public ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO){
        return categoryFacade.update(productCategoryDTO);
    }
    @POST
    @Transactional
    public ProductCategoryDTO create(ProductCategoryDTO productCategoryDTO){
        return categoryFacade.create(productCategoryDTO);
    }


}
