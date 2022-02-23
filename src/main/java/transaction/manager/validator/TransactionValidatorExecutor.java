package transaction.manager.validator;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import transaction.manager.domain.record.TransactionValidationRecord;
import transaction.manager.exception.TransactionValidationException;

@Singleton
public class TransactionValidatorExecutor {

    private final List<TransactionValidator> validators;

    @Inject
    public TransactionValidatorExecutor(final List<TransactionValidator> validators) {
        this.validators = validators;
    }

    public void validate(final TransactionValidationRecord validationRecord) throws TransactionValidationException {
        for (final TransactionValidator validator : validators) {
            validator.validate(validationRecord);
        }
    }

}
