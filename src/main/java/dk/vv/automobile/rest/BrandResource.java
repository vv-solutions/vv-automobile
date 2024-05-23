package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.BrandDTO;
import dk.vv.automobile.dtos.ProductCategoryDTO;
import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.facades.BrandFacade;
import dk.vv.automobile.facades.ProductFacade;
import jakarta.activation.MimeType;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;

@ApplicationScoped
@Resource
@Path("/brand/")
@Produces("application/json")
@Consumes("application/json")
public class BrandResource {


    private final BrandFacade brandFacade;

    @Inject
    public BrandResource(BrandFacade brandFacade) {
        this.brandFacade = brandFacade;
    }

    @GET
    @Path("/{id}")
    public BrandDTO getById(@PathParam("id") int id){
        return brandFacade.getBrandById(id);
    }

    @GET
    @Path("/all")
    public List<BrandDTO> getAll(){
        return brandFacade.getAllBrands();
    }



    @PUT
    @Path("/")
    @Transactional
    public BrandDTO update(BrandDTO brandDTO){
        return brandFacade.updateBrand(brandDTO);
    }

}
