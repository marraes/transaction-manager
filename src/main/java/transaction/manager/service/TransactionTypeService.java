package transaction.manager.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import transaction.manager.domain.entity.TransactionType;
import transaction.manager.repository.TransactionTypeRepository;
import transaction.manager.service.generic.AbstractCrudService;

@Singleton
public class TransactionTypeService extends AbstractCrudService<TransactionType> {

    @Getter(value = AccessLevel.PROTECTED, onMethod_ = { @Override })
    private final TransactionTypeRepository repository;

    @Inject
    public TransactionTypeService(TransactionTypeRepository repository) {
        this.repository = repository;
    }

}
