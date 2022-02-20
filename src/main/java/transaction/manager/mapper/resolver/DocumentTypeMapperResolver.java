package transaction.manager.mapper.resolver;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.domain.record.CustomerRecord;
import transaction.manager.domain.record.DocumentTypeRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.service.DocumentTypeService;

@Singleton
public class DocumentTypeMapperResolver extends AbstractMapperResolver {

    private final DocumentTypeService documentTypeService;

    @Inject
    public DocumentTypeMapperResolver(final DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @ObjectFactory
    public DocumentType resolveDocumentType(final DocumentTypeRecord documentTypeRecord, @TargetType final Class<DocumentType> classType) throws EntityNotFoundException {
        return resolveToEntitySafety(documentTypeService, documentTypeRecord::id, DocumentType::new, documentTypeRecord);
    }

    @ObjectFactory
    public DocumentType resolveCustomerRecordDocumentType(final CustomerRecord customerRecord, @TargetType final Class<CustomerRecord> classType) throws EntityNotFoundException {
        final DocumentTypeRecord documentTypeRecord = customerRecord.documentType();
        return resolveToEntitySafety(documentTypeService, documentTypeRecord::id, documentTypeRecord);
    }

}
