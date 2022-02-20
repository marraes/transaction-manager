package transaction.manager.service;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.transaction.Transactional;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import transaction.manager.domain.entity.Account;
import transaction.manager.domain.entity.Customer;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.repository.AccountRepository;
import transaction.manager.service.generic.AbstractCrudService;

@Singleton
public class AccountService extends AbstractCrudService<Account> {

    @Getter(value = AccessLevel.PROTECTED, onMethod_ = { @Override })
    private final AccountRepository repository;

    private final CustomerService customerService;

    @Inject
    public AccountService(final AccountRepository repository, final CustomerService customerService) {
        this.repository = repository;
        this.customerService = customerService;
    }

    @Nonnull
    @Transactional
    public Account createAccount(@Nonnull final UUID customerId, @Nonnull final String accountNumber) throws EntityNotFoundException {
        final Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Not found customer"));
        final Account account = Account.builder()
                .accountNumber(accountNumber)
                .customer(customer)
                .build();

        return getRepository().save(account);
    }

    @Nonnull
    @Transactional
    public Account findByCustomerAndAccount(@Nonnull final UUID customerId, @Nonnull final UUID accountId) throws EntityNotFoundException {
        return getRepository().findByCustomerIdAndId(customerId, accountId)
                .orElseThrow(EntityNotFoundException::new);
    }

}
