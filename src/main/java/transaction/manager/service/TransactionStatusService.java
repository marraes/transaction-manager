package transaction.manager.service;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.transaction.Transactional;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import transaction.manager.domain.entity.TransactionStatus;
import transaction.manager.repository.TransactionStatusRepository;
import transaction.manager.service.generic.AbstractCrudService;

@Singleton
public class TransactionStatusService extends AbstractCrudService<TransactionStatus> {

    @Getter(value = AccessLevel.PROTECTED, onMethod_ = { @Override })
    private final TransactionStatusRepository repository;

    @Inject
    public TransactionStatusService(TransactionStatusRepository repository) {
        this.repository = repository;
    }

    @Nonnull
    @Transactional
    public Optional<TransactionStatus> findByCode(@Nonnull final Integer dbCode) {
        return getRepository().findByCode(dbCode);
    }
}
