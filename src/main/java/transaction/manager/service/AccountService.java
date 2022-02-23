package transaction.manager.service;

import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.transaction.Transactional;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import transaction.manager.domain.entity.Account;
import transaction.manager.domain.entity.Customer;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.repository.AccountRepository;
import transaction.manager.service.generic.AbstractCrudService;

@Slf4j
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
    public Account createAccount(@Nonnull final UUID customerId, @Nonnull final String accountNumber, @Nonnull BigDecimal balance) throws EntityNotFoundException {
        log.info("createAccount step=start customerId={}", customerId);
        final Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Not found customer"));
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .customer(customer)
                .balance(balance)
                .build();

        account = getRepository().save(account);

        log.info("createAccount step=end customerId={} accountId={}", customerId, account.getId());

        return account;
    }

    @Transactional
    public Account updateBalance(final Account account, final BigDecimal transactionAmountRequested) {
        final BigDecimal newBalanceAmount = account.getBalance().add(transactionAmountRequested);
        account.setBalance(newBalanceAmount);
        return save(account);
    }

    @Nonnull
    @Transactional
    public Account findByCustomerAndAccount(@Nonnull final UUID customerId, @Nonnull final UUID accountId) throws EntityNotFoundException {
        log.trace("findByCustomerAndAccount customerId={} accountId={}", customerId, accountId);
        return getRepository().findByCustomerIdAndId(customerId, accountId)
                .orElseThrow(EntityNotFoundException::new);
    }

}
