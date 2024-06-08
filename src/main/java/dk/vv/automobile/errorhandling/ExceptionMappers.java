package dk.vv.automobile.errorhandling;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMappers {



    @ServerExceptionMapper
    public RestResponse<ProductQuantityException> mapNotFoundException(ProductQuantityException productQuantityException) {
        return RestResponse.status(Response.Status.BAD_REQUEST, productQuantityException);

    }

    @ServerExceptionMapper
    public RestResponse<ProductInactiveException> mapNotFoundException(ProductInactiveException productInactiveException) {
        return RestResponse.status(Response.Status.BAD_REQUEST, productInactiveException);
    }

    @ServerExceptionMapper
    public RestResponse<ChatException> mapChatException(ChatException chatException) {
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, chatException);
    }

    @ServerExceptionMapper
    public RestResponse<PredictionException> mapPredictionException(PredictionException predictionException) {
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, predictionException);
    }
}
