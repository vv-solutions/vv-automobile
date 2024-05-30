package dk.vv.automobile.errorhandling;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMappers {



    @ServerExceptionMapper
    public RestResponse<ProductQuantityException> mapNotFoundException(ProductQuantityException productQuantityException) {
        return RestResponse.status(Response.Status.BAD_REQUEST, productQuantityException);

    }
}
