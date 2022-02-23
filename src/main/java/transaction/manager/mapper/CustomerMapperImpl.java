package transaction.manager.mapper;

import java.time.LocalDate;
import java.util.UUID;
import javax.inject.Singleton;

import jakarta.inject.Inject;
import transaction.manager.domain.entity.Customer;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.domain.record.CustomerRecord;
import transaction.manager.domain.record.DocumentTypeRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.mapper.resolver.DocumentTypeMapperResolver;

@Singleton
public class CustomerMapperImpl implements CustomerMapper {

    private final DocumentTypeMapperResolver documentTypeMapperResolver;

    @Inject
    public CustomerMapperImpl(DocumentTypeMapperResolver documentTypeMapperResolver) {
        this.documentTypeMapperResolver = documentTypeMapperResolver;
    }

    @Override
    public Customer map(CustomerRecord customerRecord) throws EntityNotFoundException {
        if (customerRecord == null) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId(customerRecord.id());
        customer.setName(customerRecord.name());
        customer.setBirthday(customerRecord.birthday());
        customer.setDocumentNumber(customerRecord.documentNumber());
        customer.setDocumentType(documentTypeRecordToDocumentType(customerRecord.documentType()));

        return customer;
    }

    @Override
    public CustomerRecord map(Customer customer) {
        if (customer == null) {
            return null;
        }

        UUID id = null;
        String name = null;
        LocalDate birthday = null;
        String documentNumber = null;
        DocumentTypeRecord documentType = null;

        id = customer.getId();
        name = customer.getName();
        birthday = customer.getBirthday();
        documentNumber = customer.getDocumentNumber();
        documentType = documentTypeToDocumentTypeRecord(customer.getDocumentType());

        return new CustomerRecord(id, name, birthday, documentNumber, documentType);
    }

    protected DocumentType documentTypeRecordToDocumentType(DocumentTypeRecord documentTypeRecord) throws EntityNotFoundException {
        if (documentTypeRecord == null) {
            return null;
        }

        return documentTypeMapperResolver.resolveDocumentType(documentTypeRecord, DocumentType.class);
    }

    protected DocumentTypeRecord documentTypeToDocumentTypeRecord(DocumentType documentType) {
        if (documentType == null) {
            return null;
        }

        UUID id = null;
        Integer code = null;
        String description = null;

        id = documentType.getId();
        code = documentType.getCode();
        description = documentType.getDescription();

        return new DocumentTypeRecord(id, code, description);
    }
}
