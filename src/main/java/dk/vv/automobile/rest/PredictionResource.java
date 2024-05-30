package dk.vv.automobile.rest;

import dk.vv.automobile.dtos.BrandDTO;
import dk.vv.automobile.facades.BrandFacade;
import dk.vv.automobile.facades.PredictionFacade;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
@Resource
@Path("/prediction/")
@Produces("application/json")
//@Consumes("application/json")
public class PredictionResource {


    private final PredictionFacade predictionFacade;

    @Inject
    public PredictionResource(PredictionFacade predictionFacade) {
        this.predictionFacade = predictionFacade;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public BigDecimal getP(@FormParam("image") File image, @FormParam("km") Integer km, @FormParam("numberplate") String numberplate) throws IOException {

        String path = null;

        if(image != null){
            path = image.getAbsolutePath();
        }

        return predictionFacade.getPricePrediction(path,numberplate,km);
    }
}
