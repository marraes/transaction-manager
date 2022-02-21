package transaction.manager.resource.handler.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

import io.micronaut.http.HttpRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import transaction.manager.domain.dto.ApiError;

@ExtendWith(MockitoExtension.class)
class ClientErrorExceptionHandlerTest {

    @Test
    void givenClientErrorWithoutCause_whenHandleTheException_thenReturnExpectedResponse() {
        final HttpRequest requestMock = mock(HttpRequest.class);
        final Response errorResponseStub = mock(Response.class);
        final ClientErrorException errorExceptionStub = mock(ClientErrorException.class);
        final ClientErrorExceptionHandler handler = new ClientErrorExceptionHandler();

        when(errorResponseStub.getStatus()).thenReturn(404);
        when(errorExceptionStub.getResponse()).thenReturn(errorResponseStub);
        when(errorExceptionStub.getMessage()).thenReturn("Http 404 Not Found");

        final Response responseHandled = handler.handle(requestMock, errorExceptionStub);

        assertNotNull(responseHandled);
        assertNotNull(responseHandled.getEntity());
        assertEquals(ApiError.class, responseHandled.getEntity().getClass());
        assertEquals(Response.Status.NOT_FOUND, ((ApiError) responseHandled.getEntity()).getStatus());
        assertEquals("Http 404 Not Found", ((ApiError) responseHandled.getEntity()).getMessage());
        assertNull(((ApiError) responseHandled.getEntity()).getMessages());
    }

    @Test
    void givenClientErrorWithCause_whenHandleTheException_thenReturnExpectedResponse() {
        final HttpRequest requestMock = mock(HttpRequest.class);
        final Response errorResponseStub = mock(Response.class);
        final NullPointerException causeException = new NullPointerException("Something is null");
        final ClientErrorException errorExceptionStub = mock(ClientErrorException.class);
        final ClientErrorExceptionHandler handler = new ClientErrorExceptionHandler();

        when(errorResponseStub.getStatus()).thenReturn(404);
        when(errorExceptionStub.getResponse()).thenReturn(errorResponseStub);
        when(errorExceptionStub.getCause()).thenReturn(causeException);
        when(errorExceptionStub.getMessage()).thenReturn("Http 404 Not Found");

        final Response responseHandled = handler.handle(requestMock, errorExceptionStub);

        assertNotNull(responseHandled);
        assertNotNull(responseHandled.getEntity());
        assertEquals(ApiError.class, responseHandled.getEntity().getClass());
        assertEquals(Response.Status.NOT_FOUND, ((ApiError) responseHandled.getEntity()).getStatus());
        assertEquals("Http 404 Not Found: Something is null", ((ApiError) responseHandled.getEntity()).getMessage());
        assertNull(((ApiError) responseHandled.getEntity()).getMessages());
    }

}
