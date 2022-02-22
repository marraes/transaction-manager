package transaction.manager.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static transaction.manager.resource.util.ApplicationPathConstants.API_ROOT;
import static transaction.manager.resource.util.ApplicationPathConstants.DOCUMENT_TYPE_RESOURCE;

import java.util.List;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.domain.record.ResponseCollection;
import transaction.manager.repository.DocumentTypeRepository;

@MicronautTest
class DocumentTypeResourceFunctionalTest {

    @Inject
    @Client("/")
    private HttpClient client;

    @Inject
    private DocumentTypeRepository documentTypeRepository;

    /**
     * It isn't with @BeforeEach, because Micronaut put in the same transaction and doesn't do the rollback
     */
    private void setup() {
        documentTypeRepository.saveAll(
                List.of(
                        DocumentType.builder()
                                .code(1)
                                .description("ID")
                                .build(),
                        DocumentType.builder()
                                .code(2)
                                .description("CPF")
                                .build()
                )
        );
    }

    @Test
    void whenRequestListOfDocumentTypeAndNoData_thenReturnEmpty() {
        HttpResponse<ResponseCollection> response = doRequest();
        ResponseCollection<DocumentType> responseCollection = response.body();

        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(responseCollection);
        assertEquals(0, responseCollection.total());
        assertEquals(0, responseCollection.data().size());
    }

    @Test
    void whenRequestListOfDocumentTypeWithData_thenReturnCorrectly() {
        setup();
        HttpResponse<ResponseCollection> response = doRequest();
        ResponseCollection<DocumentType> responseCollection = response.body();

        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(responseCollection);
        assertEquals(2, responseCollection.total());
        assertEquals(0, responseCollection.data().size());
    }

    private HttpResponse<ResponseCollection> doRequest() {
        HttpRequest<ResponseCollection> request = HttpRequest.GET(API_ROOT + DOCUMENT_TYPE_RESOURCE);

        return client.toBlocking().exchange(request, Argument.of(ResponseCollection.class));
    }

}
