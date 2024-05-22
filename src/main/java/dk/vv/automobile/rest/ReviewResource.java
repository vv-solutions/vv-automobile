package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.dtos.ReviewDTO;
import dk.vv.automobile.entities.neo4j.ReviewProduct;
import dk.vv.automobile.facades.ProductFacade;
import dk.vv.automobile.facades.ReviewFacade;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@ApplicationScoped
@Resource
@Path("/review/")
@Produces("application/json")
@Consumes("application/json")
public class ReviewResource {

    private final ReviewFacade reviewFacade;

    private final ProductFacade productFacade;

    @Inject
    public ReviewResource(ReviewFacade reviewFacade, ProductFacade productFacade) {
        this.reviewFacade = reviewFacade;
        this.productFacade = productFacade;
    }

    @GET
    public List<ReviewProduct> getReviewProducts(){
        return reviewFacade.recommendProducts("gketteringhammj@house.gov");
    }

    @POST
    @Path("/create")
    public ReviewDTO createReview(ReviewDTO reviewDTO){
        return reviewFacade.createReview(reviewDTO);
    }

    @GET
    @Path("/{productId}")
    public List<ReviewDTO> getReviewsByProductId(@PathParam("productId") int productId){
        return reviewFacade.getReviewsByProductId(productId);
    }

    @GET
    @Path("/recommended/{productId}")
    public List<ProductDTO> getRecommendedProducts(@PathParam("productId") int productId){
        return productFacade.getProductsByIds(
                reviewFacade.recommendProducts(productId)
        );

    }
}
