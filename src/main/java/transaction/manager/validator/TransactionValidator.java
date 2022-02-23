package transaction.manager.validator;

import io.micronaut.core.annotation.Indexed;
import transaction.manager.domain.record.TransactionValidationRecord;
import transaction.manager.exception.TransactionValidationException;

@Indexed(TransactionValidator.class)
public interface TransactionValidator {

    void validate(final TransactionValidationRecord validationRecord) throws TransactionValidationException;

}
