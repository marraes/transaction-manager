package transaction.manager.mapper.resolver;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.domain.record.DocumentTypeRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.service.DocumentTypeService;

@Slf4j
@Singleton
public class DocumentTypeMapperResolver extends AbstractMapperResolver {

    private final DocumentTypeService documentTypeService;

    @Inject
    public DocumentTypeMapperResolver(final DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @ObjectFactory
    public DocumentType resolveDocumentType(final DocumentTypeRecord documentTypeRecord, @TargetType final Class<DocumentType> classType) throws EntityNotFoundException {
        log.trace("resolveDocumentTypeRecordToEntity documentTypeRecord={}", documentTypeRecord);
        return resolveToEntitySafety(documentTypeService, documentTypeRecord::id, DocumentType::new, documentTypeRecord);
    }

}
