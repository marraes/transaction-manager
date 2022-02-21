package transaction.manager.mapper.resolver;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.domain.record.DocumentTypeRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.service.DocumentTypeService;

@ExtendWith(MockitoExtension.class)
class DocumentTypeMapperResolverTest {

    @Test
    void givenDocumentTypeRecord_whenFoundEntityFromDb_thenMustReturnDocumentTypeWithNoError() {
        final UUID id = UUID.fromString("60e48c92-8440-47a0-8af4-d38d2503448f");
        final DocumentTypeRecord documentTypeRecord = new DocumentTypeRecord(id, null, null);
        final DocumentTypeService service = mock(DocumentTypeService.class);
        final DocumentTypeMapperResolver resolver = new DocumentTypeMapperResolver(service);

        when(service.findById(id)).thenReturn(Optional.of(DocumentType.builder().id(id).code(1).description("foo").build()));

        final DocumentType documentTypeResolved = assertDoesNotThrow(() -> resolver.resolveDocumentType(documentTypeRecord, DocumentType.class));

        assertNotNull(documentTypeResolved);
        assertEquals(id, documentTypeResolved.getId());
    }

    @Test
    void givenDocumentTypeRecord_whenNotFoundEntityFromDb_thenThrowNotFoundException() {
        final UUID id = UUID.fromString("60e48c92-8440-47a0-8af4-d38d2503448f");
        final DocumentTypeRecord documentTypeRecord = new DocumentTypeRecord(id, null, null);
        final DocumentTypeService service = mock(DocumentTypeService.class);
        final DocumentTypeMapperResolver resolver = new DocumentTypeMapperResolver(service);

        when(service.findById(id)).thenReturn(Optional.empty());

        final EntityNotFoundException notFoundException = assertThrows(EntityNotFoundException.class, () -> resolver.resolveDocumentType(documentTypeRecord, DocumentType.class));

        assertEquals("Not found ".concat(id.toString()), notFoundException.getMessage());
    }

    @Test
    void givenDocumentTypeRecordWithNullId_whenTryResolveEntityFromDb_thenMustReturnEmptyObject() {
        final DocumentTypeRecord documentTypeRecord = new DocumentTypeRecord(null, null, null);
        final DocumentTypeService service = mock(DocumentTypeService.class);
        final DocumentTypeMapperResolver resolver = new DocumentTypeMapperResolver(service);

        final DocumentType documentTypeResolved = assertDoesNotThrow(() -> resolver.resolveDocumentType(documentTypeRecord, DocumentType.class));

        assertNotNull(documentTypeResolved);
        assertNull(documentTypeResolved.getId());
        assertNull(documentTypeResolved.getCode());
        assertNull(documentTypeResolved.getDescription());
    }

}
