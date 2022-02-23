package transaction.manager.validator;

import java.math.BigDecimal;

import jakarta.inject.Singleton;
import transaction.manager.domain.entity.Account;
import transaction.manager.domain.record.TransactionValidationRecord;
import transaction.manager.exception.TransactionValidationException;

@Singleton
public class LimitTransactionValidator implements TransactionValidator {

    @Override
    public void validate(final TransactionValidationRecord validationRecord) throws TransactionValidationException {
        final Account account = validationRecord.account();
        final BigDecimal transactionRequestAmount = validationRecord.transactionAmount();

        if (account.getBalance().add(transactionRequestAmount).compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionValidationException("Limit not available");
        }
    }

}
