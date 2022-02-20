package transaction.manager.resource.handler.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import transaction.manager.domain.dto.ApiError;

@Produces
@Singleton
public class ClientErrorExceptionHandler implements ExceptionHandler<ClientErrorException, Response> {


    @Override
    public Response handle(HttpRequest request, ClientErrorException exception) {
        final int statusCode = exception.getResponse().getStatus();
        final Response.Status status = Response.Status.fromStatusCode(statusCode);
        String message = exception.getMessage();

        if (exception.getCause() != null) {
            message = message.concat(": ").concat(exception.getCause().getMessage());
        }

        final ApiError apiError = ApiError.builder()
                .status(status)
                .message(message)
                .build();

        return Response
                .status(status)
                .entity(apiError)
                .build();
    }

}
