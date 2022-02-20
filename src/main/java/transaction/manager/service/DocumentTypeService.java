package transaction.manager.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import transaction.manager.domain.entity.DocumentType;
import transaction.manager.repository.DocumentTypeRepository;
import transaction.manager.service.generic.AbstractCrudService;

@Singleton
public class DocumentTypeService extends AbstractCrudService<DocumentType> {

    @Getter(value = AccessLevel.PROTECTED, onMethod_ = { @Override })
    private final DocumentTypeRepository repository;

    @Inject
    public DocumentTypeService(final DocumentTypeRepository repository) {
        this.repository = repository;
    }

}
