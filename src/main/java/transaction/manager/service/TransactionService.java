package transaction.manager.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import javax.transaction.Transactional;

import io.micronaut.core.annotation.Introspected;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import transaction.manager.domain.entity.Account;
import transaction.manager.domain.entity.Transaction;
import transaction.manager.domain.entity.TransactionStatus;
import transaction.manager.domain.entity.TransactionType;
import transaction.manager.domain.enums.TransactionStatusEnum;
import transaction.manager.domain.record.TransactionRegisterRequestRecord;
import transaction.manager.exception.EntityNotFoundException;
import transaction.manager.repository.TransactionRepository;
import transaction.manager.resolver.TransactionAmountResolver;
import transaction.manager.service.generic.AbstractCrudService;

@Slf4j
@Singleton
public class TransactionService extends AbstractCrudService<Transaction> {

    @Getter(value = AccessLevel.PROTECTED, onMethod_ = { @Override })
    private final TransactionRepository repository;
    private final AccountService accountService;
    private final TransactionStatusService transactionStatusService;
    private final TransactionTypeService transactionTypeService;
    private final TransactionAmountResolver transactionAmountResolver;

    @Inject
    public TransactionService(
            final TransactionRepository repository,
            final AccountService accountService,
            final TransactionStatusService transactionStatusService,
            final TransactionTypeService transactionTypeService,
            final TransactionAmountResolver transactionAmountResolver
    ) {
        this.repository = repository;
        this.accountService = accountService;
        this.transactionStatusService = transactionStatusService;
        this.transactionTypeService = transactionTypeService;
        this.transactionAmountResolver = transactionAmountResolver;
    }

    @Transactional
    public void registerTransaction(
            @Nonnull final UUID accountId,
            @Nonnull final TransactionRegisterRequestRecord transactionData
    ) throws EntityNotFoundException {
        log.info("registerTransaction step=start accountId={} params={}", accountId, transactionData);
        final Instant eventDate = Instant.now();

        final TransactionRegistrationRequiredData transactionParams = fetchTransactionParamsData(accountId, transactionData);
        final BigDecimal resolvedAmount = transactionAmountResolver.resolveTransactionAmount(transactionParams.transactionType().getOperation(), transactionData.amount());

        Transaction transaction = Transaction.builder()
                .eventDate(eventDate)
                .amount(resolvedAmount)
                .installments(transactionData.installments())
                .type(transactionParams.transactionType())
                .status(transactionParams.transactionStatus())
                .account(transactionParams.account())
                .build();

        transaction = save(transaction);

        log.info("registerTransaction step=end accountId={} transactionId={} params={}", accountId, transaction.getId(), transactionData);
    }

    private TransactionRegistrationRequiredData fetchTransactionParamsData(
            @Nonnull final UUID accountId,
            @Nonnull final TransactionRegisterRequestRecord transactionData
    ) throws EntityNotFoundException {
        final CompletableFuture<Optional<TransactionStatus>> transactionStatusFuture = CompletableFuture
                .supplyAsync(() -> transactionStatusService.findByCode(TransactionStatusEnum.REGISTERED.getDbCode()));

        final CompletableFuture<Optional<Account>> accountFuture = CompletableFuture
                .supplyAsync(() -> accountService.findById(accountId));

        final CompletableFuture<Optional<TransactionType>> transactionTypeFuture = CompletableFuture
                .supplyAsync(() -> transactionTypeService.findById(transactionData.transactionTypeId()));

        CompletableFuture.allOf(transactionStatusFuture, accountFuture, transactionTypeFuture).join();

        final TransactionStatus transactionStatus = transactionStatusFuture.join().orElseThrow(() -> new IllegalStateException("Unexpected transaction status"));
        final Account account = accountFuture.join().orElseThrow(() -> new EntityNotFoundException("Not found customer"));
        final TransactionType transactionType = transactionTypeFuture.join().orElseThrow(() -> new EntityNotFoundException("Not found transaction type"));

        return new TransactionRegistrationRequiredData(transactionStatus, account, transactionType);
    }

    @Introspected
    private record TransactionRegistrationRequiredData(@Nonnull TransactionStatus transactionStatus, @Nonnull Account account, @Nonnull TransactionType transactionType) {
    }

}
