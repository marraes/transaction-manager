package transaction.manager.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.domain.record.DocumentTypeRecord;

class DocumentTypeMapperTest {

    @Test
    void givenDocumentType_whenMapToDocumentTypeRecord_thenMustMapCorrectly() {
        final UUID id = UUID.fromString("60e48c92-8440-47a0-8af4-d38d2503448f");
        final List<DocumentType> documentTypes = List.of(
                DocumentType.builder()
                        .id(id)
                        .code(1)
                        .description("foo")
                        .build()
        );
        final DocumentTypeMapper mapper = new DocumentTypeMapperImpl();

        final List<DocumentTypeRecord> mappedAccountRecords = mapper.map(documentTypes);

        assertNotNull(mappedAccountRecords);
        assertEquals(1, mappedAccountRecords.size());
        assertEquals(id, mappedAccountRecords.get(0).id());
        assertEquals(1, mappedAccountRecords.get(0).code());
        assertEquals("foo", mappedAccountRecords.get(0).description());
    }

}
