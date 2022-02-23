package transaction.manager.resource.handler.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import transaction.manager.domain.dto.ApiError;
import transaction.manager.exception.TransactionValidationException;

@Slf4j
@Produces
@Singleton
public class TransactionValidationExceptionHandler implements ExceptionHandler<TransactionValidationException, Response> {

    @Override
    public Response handle(HttpRequest request, TransactionValidationException exception) {
        String message = exception.getMessage();
        log.info("transactionValidationException message={}", message);
        final Response.Status responseStatus = Response.Status.BAD_REQUEST;

        final ApiError apiError = ApiError.builder()
                .status(responseStatus)
                .message(message)
                .build();

        return Response
                .status(responseStatus)
                .entity(apiError)
                .build();
    }

}
